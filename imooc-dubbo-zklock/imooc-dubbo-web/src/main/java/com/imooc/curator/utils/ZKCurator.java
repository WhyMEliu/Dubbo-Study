package com.imooc.curator.utils;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKCurator {
	
	private CuratorFramework client = null;		// zk客户端
	
	final static Logger log = LoggerFactory.getLogger(ZKCurator.class);
	
    public ZKCurator(CuratorFramework client) {
    	this.client = client;
    }
    
    /**
     * @Description: 初始化操作
     */
    public void init() {
    	// 使用命名空间
    	client = client.usingNamespace("zk-curator-connector");
	}
    
    /**
     * @Description: 判断zk是否连接
     */
    public boolean isZKAlive() {
    	return client.isStarted();
    }
}
