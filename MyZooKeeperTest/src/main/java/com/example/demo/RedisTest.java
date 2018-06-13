package com.example.demo;

import java.util.Collections;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 本文使用redis单实例结合redis的set方法和eval函数实现了一个简单的分布式锁，
 * 但是这个实现还是明显有问题的。虽然使用set方法设置了超时时间，
 * 以避免线程获取到锁后redis挂了后锁没有被释放的情况，但是超时时间设置为多少合适那？
 * 如果设置太小，可能会存在线程获取锁后执行业务逻辑时间大于锁超时时间，
 * 那么就会存在逻辑还没执行完，锁已经因为超时自动释放了，而其他线程可能获取到锁，
 * 那么之前获取锁的线程的业务逻辑的执行就没有保证原子性。

	另外还有一个问题是Lock方法里面是自旋调用tryLock进行重试，
	这就会导致像JUC中的AtomicLong一样，在高并发下多个线程竞争同一个
	资源时候造成大量线程占用cpu进行重试操作。这时候其实可以随机生成一个等待时间，
	等时间到后在进行重试，以减少潜在的同时对一个资源进行竞争的并发量。
 * @author zh
 *
 */
@Service
public class RedisTest{
	
	private JedisPool jdisPool;
	
	public RedisTest(){
		jdisPool = new JedisPool("127.0.0.1",6379);
	}
	
	private static final String LOCK_SUCCESS = "OK"; 
	private static final String SET_IF_NOT_EXIST = "NX"; 
	private static final String SET_WITH_EXPIRE_TIME = "PX"; 
	private static final Long RELEASE_SUCCESS = 1L; 
	
	private static void validParam(JedisPool jedisPool, String lockKey, String requestId, int expireTime) { 
		if (null == jedisPool) { 
			throw new IllegalArgumentException("jedisPool obj is null"); 
		} if (null == lockKey || "".equals(lockKey)) { 
			throw new IllegalArgumentException("lock key is blank"); 
		} if (null == requestId || "".equals(requestId)) { 
			throw new IllegalArgumentException("requestId is blank"); 
		} if (expireTime < 0) { 
			throw new IllegalArgumentException("expireTime is not allowed less zero"); 
		} 
	} 
	/** * * 
	 * @param jedis 
	 * * @param lockKey   key值
	 * * @param requestId  value值
	 * * @param expireTime 
	 * * @return 
	 * 
	 * public String set(final String key, final String value, final String nxxx, final String expx,final int time)
	 * 其中前面两个是key,value值；
	 * nxxx为模式，这里我们设置为NX，意思是说如果key不存在则插入该key对应的value并返回OK，否者什么都不做返回null
	 * expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
	 * expireTime 过期时间
	 * */ 
	public static boolean tryLock(JedisPool jedisPool, String lockKey, String requestId, int expireTime) { 
		validParam(jedisPool, lockKey, requestId, expireTime); 
		Jedis jedis = null; 
		try { 
			jedis = jedisPool.getResource(); 
			String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime); 
			if (LOCK_SUCCESS.equals(result)) { 
				return true; 
			} 
		} catch (Exception e) { 
			throw e; 
		} finally { 
			if (null != jedis) { 
				jedis.close(); 
			} 
		} return false; 
	} 
	/** * 
	 * * @param jedis 
	 * * @param lockKey 
	 * * @param requestId 
	 * * @param expireTime 
	 * */ 
	public static void lock(JedisPool jedisPool, String lockKey, String requestId, int expireTime) { 
		validParam(jedisPool, lockKey, requestId, expireTime); 
		while (true) { 
			if (tryLock(jedisPool, lockKey, requestId, expireTime)) { 
				return; 
			} 
		} 
	} 
	/** * 
	 * * @param jedis 
	 * * @param lockKey 
	 * * @param requestId 
	 * * @return 
	 * lua脚本对redis来说是原子操作  所以建议判断并删除使用lva脚本
	 * */ 
	public static boolean unLock(JedisPool jedisPool, String lockKey, String requestId) { 
		validParam(jedisPool, lockKey, requestId, 1);
		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end"; 
		Jedis jedis = null; 
		try { 
			jedis = jedisPool.getResource(); 
			Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId)); 
			if (RELEASE_SUCCESS.equals(result)) { 
				return true; 
			} 
		} catch (Exception e) { 
			throw e; 
		} finally { 
			if (null != jedis) { 
				jedis.close(); 
			} 
		} return false; 
	}
	public JedisPool getJdisPool() {
		return jdisPool;
	}
	
}
