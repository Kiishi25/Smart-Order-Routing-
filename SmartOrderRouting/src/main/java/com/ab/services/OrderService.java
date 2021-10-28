package com.ab.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Order;
import com.ab.entities.OrderBook;
import com.ab.entities.TradeHistory;
import com.ab.entities.User;
import com.ab.models.Action;
import com.ab.models.OrderType;
import com.ab.repositories.HistoryRepository;
import com.ab.repositories.OrderRepository;

@Service
public class OrderService {
	
	private static final Logger logger = LogManager.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRep;
	
	@Autowired
	private HistoryRepository historyRep;
	
	public OrderService() {
		BasicConfigurator.configure();
	}
	
	
	private Order addMarketOrder(OrderBook orderBook, User user, Action buyOrSell, OrderType type, int shareQuantity) {
		Order order = new Order(orderBook, user, buyOrSell, type, shareQuantity);
		return order;
	}
	
	private Order addLimitOrder(OrderBook orderBook, User user, Action buyOrSell, OrderType type,double priceLimit, int shareQuantity) {
		Order order = new Order(orderBook, user, buyOrSell, type, priceLimit, shareQuantity);
		return order;
	}
	
	private Order addHiddenOrder(OrderBook orderBook, User user, Action buyOrSell, OrderType type, double priceLimit, int shareQuantity) {
		Order order = new Order(orderBook, user, buyOrSell, type, priceLimit, shareQuantity, true);
		return order;
	}
	
	private Order addTimedOrder(OrderBook orderBook, User user, Action buyOrSell, OrderType type, double priceLimit, int shareQuantity, String auctionTime) {
		Order order = new Order(orderBook, user, buyOrSell, type, priceLimit, shareQuantity, auctionTime);
		return order;
	}
	
	public boolean  addOrder(OrderBook orderBook, User user, OrderType type, Action buyOrSell, double priceLimit, int shareQuantity, String auctionTime) {
		Order order;
		if(shareQuantity >= 1000) {
			addMultipleOrder(orderBook, user, type, buyOrSell, priceLimit, shareQuantity, auctionTime);
		}
		switch(type) {
		case Market:
			logger.info("Order Type Market being added");
			order = addMarketOrder(orderBook, user, buyOrSell, type , shareQuantity);
			break;
		case Limit:
			logger.info("Order Type Limit being added");
			order = addLimitOrder(orderBook, user, buyOrSell, type, priceLimit, shareQuantity);
			break;
		case Hidden:
			logger.info("Order Type Hidden being added");
			order = addHiddenOrder(orderBook, user, buyOrSell, type, priceLimit, shareQuantity);
			break;
		case Timed:
			logger.info("Order Type Timed being added");
			order = addTimedOrder(orderBook, user, buyOrSell, type, priceLimit, shareQuantity, auctionTime);
			break;
		default:
			logger.warn("No type found. Aborting addOrder");
			return false;
		}
		try {
			orderRep.save(order);
			return true;
		}catch(Exception e) {
			//add Log
			logger.warn("Add Order Failed");
			return false;
		}
	}
	
	private void addMultipleOrder(OrderBook orderBook, User user, OrderType type, Action buyOrSell, double priceLimit, int shareQuantity, String auctionTime) {
		logger.info("Order splitting into multiple child orders");
		int remainder = shareQuantity % 2;
		int shareQuantity1 = Math.floorDiv(shareQuantity, 2) + remainder;
		int shareQuantity2 = Math.floorDiv(shareQuantity, 2);
		addOrder(orderBook, user, type, buyOrSell, priceLimit, shareQuantity1, auctionTime);
		addOrder(orderBook, user, type, buyOrSell, priceLimit, shareQuantity2, auctionTime);
	}
	
	public TradeHistory addTradeHistory(int thisOrderID, int tradedWithID, int shareQuanitity, double value) {
		Order order = orderRep.getByOrderID(thisOrderID);
		TradeHistory history = new TradeHistory(order, tradedWithID, shareQuanitity, value);
		order.getHistory().add(history);
		if(checkFull(order)) {
			order.setStatus("Fully Filled");
		}
		historyRep.save(history);
		logger.info(history.toString() + "added to order");
		return history;
	}
	
	private boolean checkFull(Order order) {
		int sharesFilled = order.getHistory().stream().mapToInt(trade -> trade.getShareQuantity()).sum();
		return order.getShareQuantity() == sharesFilled;
	}


	public Order cancelOrder(int orderID) {
		Order cancelledOrder = orderRep.getByOrderID(orderID);
		orderRep.deleteById(orderID);
		logger.info(cancelledOrder.toString() + " removed");
		return cancelledOrder;
	}
	
	public Order updateOrder(int orderID, double limit, int shareQuantity) {
		if(limit != 0) {
			orderRep.changeOrderLimit(limit, orderID);
			logger.info("Limit updated for order: " + orderID);
			return orderRep.getByOrderID(orderID);
		}else {
			orderRep.changeOrderShareQuantity(shareQuantity, orderID);
			logger.info("Order updated for order: " + orderID);
			return orderRep.getByOrderID(orderID);
		}
	}
	
	public Order getOrder(int ID) {
		return orderRep.getByOrderID(ID);
	}
	
	public List<Order> getAllOrdersByUserID(int userID){
		return new ArrayList<Order>();
		// return orderRep.findAllByUserID(userID);
	}
	
	public List<Order> getAllOrders() {
		return orderRep.findAll();
	}
	
	public List<Order> getAllOrdersByType(OrderType type){
		return orderRep.findAllByType(type);
	}
	
	public List<Order> getAllBuyOrders(){
		return orderRep.findAllByBuyOrSell("Buy");
	}
	
	public List<Order> getAllSellOrders(){
		return orderRep.findAllByBuyOrSell("Sell");
	}
	
	public List<String> getAllOrderStatus(){
		return orderRep.getAllStatus();
	}
	
}
