package com.ab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Order;
import com.ab.entities.OrderBook;
import com.ab.entities.enums.BuyOrSell;

@Service
public class SortService {
	private List<OrderBook> orderBooks;
	
	//private List<Exchange> exchanges;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderBookService orderBookService;
	
	public String executeTrade(Order order) {
		List<Order> orderList = getBuyOrSellOrder(order.getBuyOrSell());
		
		return "";
	}
	
	private List<Order> getBuyOrSellOrder(BuyOrSell buyOrSell){
		if(buyOrSell == BuyOrSell.BUY) {
			return orderService.getAllBuyOrders();
		}else {
			return orderService.getAllBuyOrders();
		}
	}
	
	public void bestMatch() {
		Order mostRecentOrder = orderService.getMostRecentOrder();
		List<Order> compareToOrders = orderService.getListOfPossibleTrades(mostRecentOrder.getOrderBook().getOrderBookID(), mostRecentOrder.getBuyOrSell(),mostRecentOrder.getPriceLimit());
		
	}
}
