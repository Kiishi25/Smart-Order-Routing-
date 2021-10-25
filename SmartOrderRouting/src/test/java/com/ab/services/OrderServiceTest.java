package com.ab.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ab.entities.Order;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	public void addMarketOrder() {
		Order order = new Order("Buy", "Market",100);
		assertEquals(true,order.toString());
	}
	
	@Test
	public void addHiddenOrder() {
		Order order = new Order("Buy","Hidden",50,100,true);
		assertEquals(true,order.toString());
	}
	
//	@Test
//	public void addOrderHistory() {
//		int orderID = 4;
//		int orderID2 = 2;
////		Order order2 = new Order("Sell",100, 100);
////		orderService.addOrder(order2);
//		assertEquals("",orderService.addOrderHistory(orderService.getOrder(orderID).getOrderID(), orderService.getOrder(orderID2).getOrderID(), 100, 100).toString());
//	}
	
//	@Test
//	public void cancelOrder() {
//		Order order = new Order("Buy",100, 100);
//		int orderID = 1;
//		assertEquals(order.toString(),orderService.cancelOrder(orderID).toString());
//	}
//	
//	@Test
//	public void updateOrderLimit() {
//		int orderID = 1;
//		double newLimit = 200;
//		orderService.updateOrder(orderID, newLimit);
//		assertEquals(200, orderService.getOrder(orderID).getPriceLimit());
//	}
//	
//	@Test
//	public void updateOrderShareQuantity() {
//		int orderID = 1;
//		int newShareQuantity = 300;
//		orderService.updateOrder(orderID, newShareQuantity);
//		assertEquals(300, orderService.getOrder(orderID).getShareQuantity());
//	}
//	
//	@Test
//	public void getOrder() {
//		Order order = new Order("Buy",100, 100);
//		int orderID = 1;
//		assertEquals(order.toString(), orderService.getOrder(orderID).toString());
//	}
//	
//	@Test
//	public void getAllOrders() {
//		assertEquals(1, orderService.getAllOrders().size());
//	}
//	
//	@Test
//	public void getAllBuyOrders() {
//		assertEquals(1, orderService.getAllBuyOrders().size());
//	}
//
//	@Test
//	public void getAllSellOrders() {
//		assertEquals(0, orderService.getAllSellOrders().size());
//	}
//	
//	@Test
//	public void getAllOrderStatus() {
//		assertEquals(2, orderService.getAllOrderStatus().size());
//	}
}
