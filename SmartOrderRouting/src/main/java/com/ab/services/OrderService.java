package com.ab.services;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Order;
import com.ab.entities.OrderHistory;
import com.ab.repositories.HistoryRepository;
import com.ab.repositories.OrderRepository;

@Service
public class OrderService {
	private static final Logger logger = LogManager.getLogger(OrderService.class);
	@Autowired
	private OrderRepository orderRep;
	
	@Autowired
	private HistoryRepository historyRep;
	
	
	public boolean addOrder(Order order) {
		try {
			orderRep.save(order);
			return true;
		}catch(Exception e) {
			//add Log
			logger.info("Add Order Failed");
			return false;
		}
	}
	
	public OrderHistory addOrderHistory(int orderID1, int orderID2, int shareQuanitity, double value) {
		Order order = orderRep.getByOrderID(orderID1);
		OrderHistory history = new OrderHistory(order,orderID2, shareQuanitity, value);
		historyRep.save(history);
		return history;
	}
	
	public Order cancelOrder(int orderID) {
		Order cancelledOrder = orderRep.getByOrderID(orderID);
		orderRep.deleteById(orderID);
		return cancelledOrder;
	}
	
	public Order updateOrder(int orderID, double limit, int shareQuantity) {
		orderRep.changeOrderLimit(limit, orderID);
		orderRep.changeOrderShareQuantity(shareQuantity, orderID);
		return orderRep.getByOrderID(orderID);
	}
	
	public Order getOrder(int ID) {
		return orderRep.getByOrderID(ID);
	}
	
	public List<Order> getAllOrders() {
		return orderRep.findAll();
	}
	
	public List<Order> getAllBuyOrders(){
		return orderRep.findAllByType("Buy");
	}
	
	public List<Order> getAllSellOrders(){
		return orderRep.findAllByType("Sell");
	}
	
	public List<String> getAllOrderStatus(){
		return orderRep.getAllStatus();
	}
	
}
