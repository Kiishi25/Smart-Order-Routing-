package com.ab.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ab.entities.Order;
import com.ab.entities.OrderBook;
import com.ab.models.Action;
import com.ab.models.OrderType;
import com.ab.repositories.OrderBookRepository;
import com.ab.repositories.UserRepository;
@SpringBootTest
class DataBasePopulator {
	private final String[] instrumentNames = {
			"IAG", "SMT", "BOO", "THG", "ARB",
			"ASC", "EZJ", "RIO", "TUI", "LLOY"};
	
	private final String username = "ADMIN";
	@Autowired
	private OrderBookRepository orderBookRep;
	
	@Autowired
	private UserRepository userRep;
	
	@Test
	void test() {
		for(String name : instrumentNames) {
			OrderBook orderBook = new OrderBook(name);
			for(int i = 0; i < 10; i++) {
				Order randomOrder;
				Action buyOrSell;
				OrderType type;
				int shareQuantity = (int) (Math.random() * (1000 -1 + 1) + 1);
				if(Math.floor(Math.random() * (2+1) + 0) == 0) {
					buyOrSell = Action.BUY;
				}else {
					buyOrSell = Action.SELL;
				}
				if(Math.floor(Math.random() * (3+1) + 0) == 0) {
					//Market
					type = OrderType.Market;
					randomOrder = new Order(orderBook, userRep.getUserByUsername(username),buyOrSell, type, shareQuantity);
				}else {
					//Limit
					type = OrderType.Limit;
					double limit = (Math.random() * (300 -1 + 1) + 1);
					randomOrder = new Order(orderBook, userRep.getUserByUsername(username),buyOrSell, type, limit, shareQuantity);
				}
				orderBook.getOrders().add(randomOrder);
			}
			orderBookRep.save(orderBook);
		}
	}

}
