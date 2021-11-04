package com.ab.services;

import java.time.LocalDateTime;
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
import com.ab.entities.enums.BuyOrSell;
import com.ab.entities.enums.OrderStatus;
import com.ab.entities.enums.OrderType;
import com.ab.repositories.TradeHistoryRepository;
import com.ab.repositories.OrderRepository;

@Service
public class OrderService {
	private static final Logger logger = LogManager.getLogger(OrderService.class);

	@Autowired
	private OrderBookService orderBookService;

	@Autowired
	private OrderRepository orderRep;
	
	@Autowired
	private TradeHistoryRepository historyRep;

	
	public OrderService() {
		BasicConfigurator.configure();
	}
	
	private Order addMarketOrder(OrderBook orderBook, User user, BuyOrSell buyOrSell, int shareQuantity) {
		Order order = new Order(orderBook, user, buyOrSell, OrderType.MARKET, shareQuantity);
		return order;
	}
	
	private Order addLimitOrder(OrderBook orderBook, User user, BuyOrSell buyOrSell, double priceLimit, int shareQuantity) {
		Order order = new Order(orderBook, user, buyOrSell, OrderType.LIMIT, priceLimit, shareQuantity);
		return order;
	}
	
	private Order addHiddenOrder(OrderBook orderBook, User user, BuyOrSell buyOrSell, double priceLimit, int shareQuantity) {
		Order order = new Order(orderBook, user, buyOrSell, OrderType.HIDDEN, priceLimit, shareQuantity, true);
		return order;
	}
	
	private Order addTimedOrder(OrderBook orderBook, User user, BuyOrSell buyOrSell, double priceLimit, int shareQuantity, String auctionTime) {
		Order order = new Order(orderBook, user, buyOrSell, OrderType.TIMED, priceLimit, shareQuantity, auctionTime);
		return order;
	}
	
	public boolean addOrder(OrderBook orderBook, User user, OrderType type, BuyOrSell buyOrSell, double priceLimit, int shareQuantity, String auctionTime) {
		orderBook = orderBookService.create(orderBook);

		Order order;
		if(shareQuantity >= 1000) {
			addMultipleOrder(orderBook, user, type, buyOrSell, priceLimit, shareQuantity, auctionTime);
			return true;
		}
		switch(type) {
		case MARKET:
			logger.info("Order Type Market being added");
			order = addMarketOrder(orderBook, user, buyOrSell, shareQuantity);
			break;
		case LIMIT:
			logger.info("Order Type Limit being added");
			order = addLimitOrder(orderBook, user, buyOrSell, priceLimit, shareQuantity);
			break;
		case HIDDEN:
			logger.info("Order Type Hidden being added");
			order = addHiddenOrder(orderBook, user, buyOrSell, priceLimit, shareQuantity);
			break;
		case TIMED:
			logger.info("Order Type Timed being added");
			order = addTimedOrder(orderBook, user, buyOrSell, priceLimit, shareQuantity, auctionTime);
			break;
		default:
			logger.warn("No type found. Aborting addOrder");
			return false;
		}
		try {
			System.out.println(order);
			orderRep.save(order);

			return true;
		}catch(Exception e) {
			//add Log
			logger.warn(e.toString());
			logger.warn("Add Order Failed");
			return false;
		}
	}
	
	private void addMultipleOrder(OrderBook orderBook, User user, OrderType type, BuyOrSell buyOrSell, double priceLimit, int shareQuantity, String auctionTime) {
		logger.info("Order splitting into multiple child orders");
		int remainder = shareQuantity % 2;
		int shareQuantity1 = Math.floorDiv(shareQuantity, 2) + remainder;
		int shareQuantity2 = Math.floorDiv(shareQuantity, 2);
		addOrder(orderBook, user, type, buyOrSell, priceLimit, shareQuantity1, auctionTime);
		addOrder(orderBook, user, type, buyOrSell, priceLimit, shareQuantity2, auctionTime);
	}
	
	public TradeHistory addTradeHistory(int thisOrderID, int tradedWithID, int shareQuantity, double value) {
		Order order = orderRep.getByOrderID(thisOrderID);
		Order tradedWithOrder = orderRep.getByOrderID(tradedWithID);
		TradeHistory history = new TradeHistory(order, tradedWithOrder, shareQuantity, value);
		order.getHistory().add(history);
		if(checkFull(order)) {
			order.setStatus(OrderStatus.FULL);
		}
		historyRep.save(history);
		logger.info(history.toString() + "added to order");
		return history;
	}
	
	private boolean checkFull(Order order) {
		int sharesFilled = order.getHistory().stream().mapToInt(trade -> trade.getShareQuantity()).sum();
		return order.getShareQuantity() == sharesFilled;
	}

	public boolean executeOrder(String username) {
		// get available buy/sell order by this user
		// for each orders
		// // select all available buy/sell order with matching instrument code as above and not by this user (order by earliest, lowest price)
		// // update sell order quantity
		// // update buy order quantity
		// // store in trade history

		List<Order> submittedOrders = orderRep.findAllByUsername(username);
		submittedOrders.stream()
			.forEach((submittedOrder) -> {
				List<Order> matchedOrders = new ArrayList<>();
				
				if (submittedOrder.getBuyOrSell() == BuyOrSell.BUY) {
					matchedOrders = orderRep.findAllBuyByInstrumentCode(submittedOrder.getOrderBook().getInstrument().getCode(), username);
				} else if (submittedOrder.getBuyOrSell() == BuyOrSell.SELL) {
					matchedOrders = orderRep.findAllSellByInstrumentCode(submittedOrder.getOrderBook().getInstrument().getCode(), username);
				}

				matchedOrders.stream()
					.forEach((matchedOrder) -> {
						if (submittedOrder.getShareQuantity() > matchedOrder.getShareQuantity()) {
							// update submittedOrder
							// reduce qty

							// update matchedOrder
							// change status
							// remove qty
						} else if (submittedOrder.getShareQuantity() < matchedOrder.getShareQuantity()) {
							// update submittedOrder
							// change status
							// remove qty

							// update matchedOrder
							// reduce qty
						} else {
							// update submittedOrder
							// change status
							// remove qty

							// update matchedOrder
							// change status
							// remove qty
						}
					});
			});

		return false;
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
	
	public List<Order> getAllOrdersByUsername(String username){
		return orderRep.findAllByUsername(username);
	}
	
	public List<Order> getAllOrders() {
		return orderRep.findAll();
	}
	
	public List<Order> getAllOrdersByType(OrderType type){
		return orderRep.findAllByType(type);
	}
	
	public List<Order> getAllBuyOrders(){
		return orderRep.findAllByBuyOrSell(BuyOrSell.BUY);
	}
	
	public List<Order> getAllSellOrders(){
		return orderRep.findAllByBuyOrSell(BuyOrSell.SELL);
	}
	
	public List<String> getAllOrderStatus(){
		return orderRep.getAllStatus();
	}

	public List<Order> getOrdersByUsername(String username) {
		return orderRep.findAllByUsername(username);
	}

//	public List<Order> getAllOrdersInLimit(BuyOrSell buyOrSell, double limit){
//		String operator;
//		if(buyOrSell == BuyOrSell.BUY) {
//			//operator = "<="
//		}else {
//			
//		}
//		return orderRep.findAllBuyBuyOrSellWithinLimit(buyOrSell,operator,limit);
//	}	
	
	public List<TradeHistory> getAllTradesForOrder(int orderID){
		return historyRep.findAllByOrderId(orderID);
	}

	public Order getMostRecentOrder() {
		return orderRep.getByTimeStamp(BuyOrSell.BUY,LocalDateTime.now());
	}

	public void cancelOrdersByUsername(String username) {
		orderRep.deleteAllByUsername(username);		
	}
	
	public List<Order> getListOfPossibleTrades(int orderBookID,BuyOrSell buyOrSell, double priceLimit) {
		if(buyOrSell == BuyOrSell.BUY) {
			if(priceLimit!= 0) {
				return orderRep.findPossibleSellOrdersForOrderBookID(orderBookID, buyOrSell, priceLimit);
			}else {
				return orderRep.findPossibleSellOrdersForOrderBookID(orderBookID, buyOrSell);
			}
		}else {
			if(priceLimit!= 0) {
				return orderRep.findPossibleBuyOrdersForOrderBookID(orderBookID, buyOrSell, priceLimit);
			}else {
				return orderRep.findPossibleBuyOrdersForOrderBookID(orderBookID, buyOrSell);
			}
		}
	}
	
}
