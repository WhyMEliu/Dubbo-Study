package com.imooc.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.curator.utils.DistributedLock;
import com.imooc.item.service.ItemsService;
import com.imooc.order.service.OrdersService;
import com.imooc.web.service.CulsterService;

@Service
public class CulsterServiceImpl implements CulsterService {

	final static Logger log = LoggerFactory.getLogger(CulsterServiceImpl.class);
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private DistributedLock distributedLock;
	
	@Override
    @Transactional
	public boolean displayBuy(String itemId) {
		
//		System.out.println("linux-pc...");
		System.out.println("windows-pc...");
		
		// 执行订单流程之前使得当前业务获得分布式锁
		distributedLock.getLock();
		
		int buyCounts = 6;
		
		// 1. 判断库存
		int stockCounts = itemsService.getItemCounts(itemId);
		if (stockCounts < buyCounts) {
			log.info("库存剩余{}件，用户需求量{}件，库存不足，订单创建失败...", 
					stockCounts, buyCounts);
			// 释放锁，让下一个请求获得锁
			distributedLock.releaseLock();
			return false;
		}
		
		// 2. 创建订单
		boolean isOrderCreated = ordersService.createOrder(itemId);
		
		// 模拟处理业务需要3秒
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			distributedLock.releaseLock();
		}
		
		// 3. 创建订单成功后，扣除库存
		if (isOrderCreated) {
			log.info("订单创建成功...");
			itemsService.displayReduceCounts(itemId, buyCounts);
		} else {
			log.info("订单创建失败...");
			// 释放锁，让下一个请求获得锁
			distributedLock.releaseLock();
			return false;
		}
		
		// 释放锁，让下一个请求获得锁
		distributedLock.releaseLock();
		return true;
	}

}
