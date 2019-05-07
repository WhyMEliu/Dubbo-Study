package com.imooc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.common.utils.IMoocJSONResult;
import com.imooc.curator.utils.ZKCurator;
import com.imooc.item.pojo.Items;
import com.imooc.item.service.ItemsService;
import com.imooc.order.pojo.Orders;
import com.imooc.order.service.OrdersService;
import com.imooc.web.service.CulsterService;

@Controller
public class PayController {
	
	final static Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private CulsterService culsterService;
	
	@Autowired
	private ZKCurator zkCurator;
	
	@RequestMapping("/test")
	@ResponseBody
	public IMoocJSONResult test() {
		
		return IMoocJSONResult.ok("test");
	}
	
	@RequestMapping("/item")
	@ResponseBody
	public IMoocJSONResult getItemById(String id) {
		
		Items item = itemsService.getItem(id);
		
		return IMoocJSONResult.ok(item);
	}
	
	@RequestMapping("/order")
	@ResponseBody
	public IMoocJSONResult getOrderById(String id) {
		
		Orders order = ordersService.getOrder(id);
		
		return IMoocJSONResult.ok(order);
	}
	
	@RequestMapping("/buy")
	@ResponseBody
	public IMoocJSONResult buy(String itemId) {
		boolean result = culsterService.displayBuy(itemId);
		return IMoocJSONResult.ok(result ? "订单创建成功..." : "订单创建失败...");
	}
	
	/**
	 * @Description: 模拟集群下的数据不一致
	 */
	@RequestMapping("/buy2")
	@ResponseBody
	public IMoocJSONResult buy2(String itemId) {
		boolean result = culsterService.displayBuy(itemId);
		return IMoocJSONResult.ok(result ? "订单创建成功..." : "订单创建失败...");
	}
	
	/**
	 * @Description: 判断zk是否连接
	 */
	@RequestMapping("/isZKAlive")
	@ResponseBody
	public IMoocJSONResult isZKAlive() {
		boolean isAlive = zkCurator.isZKAlive();
		String result = isAlive ? "连接" : "断开";
		return IMoocJSONResult.ok(result);
	}
}
