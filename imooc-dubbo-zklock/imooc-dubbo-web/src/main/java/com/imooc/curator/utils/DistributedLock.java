package com.imooc.curator.utils;

import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 分布式锁的实现工具类
 */
public class DistributedLock {
	
	private CuratorFramework client = null;		// zk客户端
	
	final static Logger log = LoggerFactory.getLogger(DistributedLock.class);
	
	// 用于挂起当前请求，并且等待上一个分布式锁释放
	private static CountDownLatch zkLocklatch = new CountDownLatch(1);
	
	// 分布式锁的总节点名
	private static final String ZK_LOCK_PROJECT = "imooc-locks";
	// 分布式锁节点
	private static final String DISTRIBUTED_LOCK = "distributed_lock";
	
	// 构造函数
    public DistributedLock(CuratorFramework client) {
    	this.client = client;
    }
    
    /**
     * @Description: 初始化锁
     */
    public void init() {
		
    	// 使用命名空间
    	client = client.usingNamespace("ZKLocks-Namespace");

    	/**
    	 * 创建zk锁的总节点，相当于eclipse的工作空间下的项目
    	 * 		ZKLocks-Namespace
    	 * 			|
    	 * 			 —— imooc-locks
    	 * 					|
    	 * 					 —— distributed_lock
    	 */
		try {
			if (client.checkExists().forPath("/" + ZK_LOCK_PROJECT) == null) {
				client.create()
						.creatingParentsIfNeeded()
						.withMode(CreateMode.PERSISTENT)
						.withACL(Ids.OPEN_ACL_UNSAFE)
						.forPath("/" + ZK_LOCK_PROJECT);
			}
			// 针对zk的分布式锁节点，创建相应的watcher事件监听
			addWatcherToLock("/" + ZK_LOCK_PROJECT);
			
		} catch (Exception e) {
			log.error("客户端连接zookeeper服务器错误... 请重试...");
		}
	}
	
	/**
	 * @Description: 获得分布式锁
	 */
	public void getLock() {
		// 使用死循环，当且仅当上一个锁释放并且当前请求获得锁成功后才会跳出
		while (true) {
			try {
				client.create()
						.creatingParentsIfNeeded()
						.withMode(CreateMode.EPHEMERAL)
						.withACL(Ids.OPEN_ACL_UNSAFE)
						.forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK);
				log.info("获得分布式锁成功...");
				return;										// 如果锁的节点能被创建成功，则锁没有被占用
			} catch (Exception e) {
				log.info("获得分布式锁失败...");
				try {
					// 如果没有获取到锁，需要重新设置同步资源值
					if(zkLocklatch.getCount() <= 0){
						zkLocklatch = new CountDownLatch(1);
					}
					// 阻塞线程
					zkLocklatch.await();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description: 释放分布式锁
	 */
	public boolean releaseLock() {
		try {
			if (client.checkExists().forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK) != null) {
				client.delete().forPath("/" + ZK_LOCK_PROJECT + "/" + DISTRIBUTED_LOCK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		log.info("分布式锁释放完毕");
		return true;
	}
	
	/**
	 * @Description: 创建watcher监听
	 */
	public void addWatcherToLock(String path) throws Exception {
		final PathChildrenCache cache = new PathChildrenCache(client, path, true);
		cache.start(StartMode.POST_INITIALIZED_EVENT);
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
					String path = event.getData().getPath();
					log.info("上一个会话已释放锁或该会话已断开, 节点路径为: " + path);
					if(path.contains(DISTRIBUTED_LOCK)) {
						log.info("释放计数器, 让当前请求来获得分布式锁...");						
						zkLocklatch.countDown();
					}
				}
			}
		});
	}
}
