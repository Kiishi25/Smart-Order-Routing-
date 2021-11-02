package com.ab.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ab.entities.User;
import com.ab.entities.enums.BuyOrSell;
import com.ab.entities.enums.OrderType;

@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
class OrderServiceTest {

	private static final String instrumentName = "Test";
	
	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderBookService orderBookService;
	
	private final User user = userService.getUser("TestUser");
	
	@Test
	@Order(1)
	public void addBuyMarketOrder() {
		orderService.addOrder(orderBookService.findOrderBook(instrumentName), user, OrderType.MARKET, BuyOrSell.BUY, 0, 100, null);
		assertEquals("", orderService.getAllOrders().toString());
	}
	
	@Test
	@Order(2)
	public void addSellMarketOrder() {
		orderService.addOrder(orderBookService.findOrderBook(instrumentName), user, OrderType.MARKET, BuyOrSell.SELL, 0, 100, null);
		assertEquals("", orderService.getAllOrders().toString());
	}
	
	@Test
	@Order(3)
	public void addBuyLimitOrder() {
		orderService.addOrder(orderBookService.findOrderBook(instrumentName), user, OrderType.MARKET, BuyOrSell.BUY, 30, 100, null);
		assertEquals("", orderService.getAllOrders().toString());
	}
	
	@Test
	@Order(4)
	public void addSellLimitOrder() {
		orderService.addOrder(orderBookService.findOrderBook(instrumentName), user, OrderType.MARKET, BuyOrSell.SELL, 30, 100, null);
		assertEquals("", orderService.getAllOrders().toString());
	}
	
	@Test
	@Order(5)
	public void addOrderHistory() {
		int orderID = 4;
		int orderID2 = 2;
		assertEquals("",orderService.addTradeHistory(orderService.getOrder(orderID).getOrderID(), orderService.getOrder(orderID2).getOrderID(), 100, 100).toString());
	}
	
	
	@Test
	@Order(6)
	public void updateOrderLimit() {
		int orderID = 1;
		double newLimit = 200;
		orderService.updateOrder(orderID, newLimit, 0);
		assertEquals(200, orderService.getOrder(orderID).getPriceLimit());
	}
	
	@Test
	@Order(7)
	public void updateOrderShareQuantity() {
		int orderID = 1;
		int newShareQuantity = 300;
		orderService.updateOrder(orderID,0, newShareQuantity);
		assertEquals(300, orderService.getOrder(orderID).getShareQuantity());
	}
	
	@Test
	@Order(8)
	public void getOrder() {
		int orderID = 1;
		assertEquals("", orderService.getOrder(orderID).toString());
	}
	
	@Test
	@Order(9)
	public void getAllOrders() {
		assertEquals(1, orderService.getAllOrders().size());
	}
	
	@Test
	@Order(10)
	public void getAllBuyOrders() {
		assertEquals(1, orderService.getAllBuyOrders().size());
	}

	@Test
	@Order(11)
	public void getAllSellOrders() {
		assertEquals(0, orderService.getAllSellOrders().size());
	}
	
	@Test
	@Order(12)
	public void getAllOrderStatus() {
		assertEquals(2, orderService.getAllOrderStatus().size());
	}
	
	@Test
	@Order(13)
	public void cancelOrder() {
		int orderID = 1;
		assertEquals("",orderService.cancelOrder(orderID).toString());
	}
}
