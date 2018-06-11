package com.example.demo.controller.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;

public class DistributedLock implements Lock,Watcher{

	private ZooKeeper zk;
	//跟节点
	private String root = "/locks";
	//竞争资源的标志
	private String lockName;
	//等待的前一个锁
	private String waitNode;
	//当前锁
	private String myZnode;
	private CountDownLatch latch;//计数器
	private CountDownLatch connectedSignal=new CountDownLatch(1);
	private int sessionTimeout = 15000;
	
	
//	@Value("${spring.data.solr.zk-host}")
	String hostPort = "127.0.0.1:2181";
	
	public DistributedLock(String lockName) {
		this.lockName=lockName;
		try {
			zk = new ZooKeeper(hostPort,sessionTimeout,this);
			connectedSignal.await();
			Stat stat = zk.exists(root, false);
			if(stat == null) {
				//创建根节点
				zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//				zk.create(path, data, acl, createMode)
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void process(WatchedEvent event) {
		//建立连接用
		if(event.getState() == KeeperState.SyncConnected) {
			connectedSignal.countDown();
			return;
		}
		//其他线程放弃锁的标志
		if(this.latch != null) {
			this.latch.countDown();
		}
		
	}
	
	@Override
	public void lock() {
		try {
			if(this.tryLock()) {
				System.out.println("Thread" + Thread.currentThread().getId()+
						" "+myZnode + "get lock true");
				return;
			} else {
				waitForLock(waitNode,sessionTimeout);//等待锁
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		try {
			String splitStr = "_lock_";
			if(lockName.contains(splitStr)) {
				throw new NullPointerException();
			}
			myZnode = zk.create(root+"/"+lockName, new byte[0], 
					ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println(myZnode+ " is created");
			//取出所有子节点
			List<String> subNodes = zk.getChildren(root, false);
			//取出所有lockName的锁
			List<String> lockObjectNodes = new ArrayList<String>();
			for(String node : subNodes) {
				String _node = node.split(splitStr)[0];
				if(_node.equals(lockName)) {
					lockObjectNodes.add(node);
				}
			}
			Collections.sort(lockObjectNodes);
			//如果是最小节点则获得锁
			if(myZnode.equals(root+"/"+lockObjectNodes.get(0))) {
				System.out.println(myZnode+ "=="+lockObjectNodes.get(0));
				return true;
			}
			//如果不是最小节点，找到比自己小1的节点
			String subMyZnode = myZnode.substring(myZnode.lastIndexOf("/")+1);
			//找到前一个节点
			waitNode = lockObjectNodes.get(Collections.binarySearch(lockObjectNodes, subMyZnode)-1);
		}catch(KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		try {
			if(this.tryLock()){
				return true;
			}
			return waitForLock(waitNode,time);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean waitForLock(String lower,long awiteTime) throws 
		InterruptedException,KeeperException{
		Stat stat = zk.exists(root+"/"+lower, true);
		if(stat != null) {
			System.out.println("Thread " + Thread.currentThread().getId() + 
					" waiting for " + root + "/" + lower);
			this.latch = new CountDownLatch(1);
			this.latch.await(awiteTime,TimeUnit.MILLISECONDS);
			this.latch = null;
		}
		return true;
	}
	

	@Override
	public void unlock() {
		try {
			System.out.println("unlock"+myZnode);
			zk.delete(myZnode, -1);
			zk.close();
		}catch(KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Condition newCondition() {
		return null;
	}

}
