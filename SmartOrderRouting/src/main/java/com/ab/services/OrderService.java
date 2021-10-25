package com.ab.services;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Order;
import com.ab.entities.TradeHistory;
import com.ab.repositories.HistoryRepository;
import com.ab.repositories.OrderRepository;

@Service
public class OrderService {
	private static final Logger logger = LogManager.getLogger(OrderService.class);
	@Autowired
	private OrderRepository orderRep;
	
	@Autowired
	private HistoryRepository historyRep;
	
	
	private Order addMarketOrder(String buyOrSell, String type, int shareQuantity) {
		Order order = new Order(buyOrSell, type, shareQuantity);
		return order;
	}
	
	private Order addLimitOrder(String buyOrSell, String type,double priceLimit, int shareQuantity) {
		Order order = new Order(buyOrSell, type, priceLimit, shareQuantity);
		return order;
	}
	
	private Order addHiddenOrder(String buyOrSell, String type, double priceLimit, int shareQuantity) {
		Order order = new Order(buyOrSell, type, priceLimit, shareQuantity, true);
		return order;
	}
	
	private Order addTimedOrder(String buyOrSell, String type, double priceLimit, int shareQuantity, String auctionTime) {
		Order order = new Order(buyOrSell, type, priceLimit, shareQuantity, auctionTime);
		return order;
	}
	
	public boolean  addOrder(String type, String buyOrSell, double priceLimit, int shareQuantity, String auctionTime) {
		Order order;
		switch(type) {
		case "Market":
			order = addMarketOrder(buyOrSell, type , shareQuantity);
			break;
		case "Limit":
			order = addLimitOrder(buyOrSell, type, priceLimit, shareQuantity);
			break;
		case "Hidden":
			order = addHiddenOrder(buyOrSell, type, priceLimit, shareQuantity);
			break;
		case "Timed":
			order = addTimedOrder(buyOrSell, type, priceLimit, shareQuantity, auctionTime);
			break;
		default:
			order = null;
		}
		try {
			orderRep.save(order);
			return true;
		}catch(Exception e) {
			//add Log
			logger.info("Add Order Failed");
			return false;
		}
	}
	
	public TradeHistory addTradeHistory(int orderID1, int orderID2, int shareQuanitity, double value) {
		Order order = orderRep.getByOrderID(orderID1);
		TradeHistory history = new TradeHistory(order,orderID2, shareQuanitity, value);
		historyRep.save(history);
		return history;
	}
	
	public Order cancelOrder(int orderID) {
		Order cancelledOrder = orderRep.getByOrderID(orderID);
		orderRep.deleteById(orderID);
		return cancelledOrder;
	}
	
	public Order updateOrder(int orderID, double limit, int shareQuantity) {
		if(limit != 0) {
			orderRep.changeOrderLimit(limit, orderID);
			return orderRep.getByOrderID(orderID);
		}else {
			orderRep.changeOrderShareQuantity(shareQuantity, orderID);
			return orderRep.getByOrderID(orderID);
		}
	}
	
	public Order getOrder(int ID) {
		return orderRep.getByOrderID(ID);
	}
	
	public List<Order> getAllOrdersByUserID(int userID){
		return orderRep.findAllByUserID(userID);
	}
	
	public List<Order> getAllOrders() {
		return orderRep.findAll();
	}
	
	public List<Order> getAllOrdersByType(String type){
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
