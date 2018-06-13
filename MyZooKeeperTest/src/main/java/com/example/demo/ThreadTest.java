package com.example.demo;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import redis.clients.jedis.JedisPool;

public class ThreadTest {
	
	public ThreadTest(){
	}
	public ThreadTest(String name,int age) {
		System.out.println(name+"---"+age);
	}

	public static void main(String[] args) {
		String an = "32313";
		
		
		IntTest1 t1 = ()->{
			return "test01"+an;
		};
		
		IntTest1 t11 = new IntTest1() {
			@Override
			public String test01() {
				return an;
			}
		};
//		Optional   p = new Function ();
		System.out.println(t1.test01());
		String name = "hhhh";
		
		IntTest2 t2 = (names)->{
			return names;
		};
		
		System.out.println(t2.t_b());
		System.out.println(t2.t_a(name));
		
		FactoryTest ft = ThreadTest::new;
		ThreadTest t = ft.factory("adfaf", 23002);
		t.print();
		
		RedisTest redis = new RedisTest();
		JedisPool jedis = redis.getJdisPool();
		
		redis.lock(jedis, "zhanghao001", "nihao", 3000000);
		
//		Thread t3 = new Thread(()->{
//			redis.lock(jedis, "zhanghao001", "nihao", 3000000);
//			System.out.println("-- t3 -- lock -- end");
//		});
//		t3.start();
		
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		redis.unLock(jedis, "zhanghao001", "nihao");
		
		System.out.println("all -- end");
		
	}

	public void print() {
		System.out.println("===== this is ::");
	}
}
