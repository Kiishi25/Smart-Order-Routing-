package com.ab.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ab.entities.Instrument;
import com.ab.entities.Order;
import com.ab.entities.OrderBook;
import com.ab.entities.enums.BuyOrSell;
import com.ab.entities.enums.OrderType;
import com.ab.repositories.InstrumentRepository;
import com.ab.repositories.OrderBookRepository;
import com.ab.repositories.UserRepository;

@SpringBootTest
class DataBasePopulator {
	private final String[] instrumentCodes = {
			"IAG", "SMT", "BOO", "THG", "ARB",
			"ASC", "EZJ", "RIO", "TUI", "LLOY"};

	private List<Instrument> instrumentsList = new ArrayList<>(){{
		add(new Instrument("IAG", "International Consolidated Airlns Grp", 163.80, 23509387, 162.06, 164.80, 160.64, +1.05));
		add(new Instrument("SMT", "Trading Group Limited", 1501.50, 3532569, 1485.00, 1501.50, 1478.00, +0.87));
		add(new Instrument("BOO", "Boohoo Group PLC", 181.75, 4269762, 183.95, 185.35 , 181.75, -1.76));
		add(new Instrument("THG", "The Hut Group", 216.60, 12901561, 222.40, 228.60, 210.20, -3.30));
		add(new Instrument("ARB", "ARB TRADING LIMITED", 122.00, 2891306, 122.00, 127.00 , 119.00, -1.61));
		add(new Instrument("ASC", "ASOS PLC", 2482.00 , 797690, 2477.00, 2526.00, 2455.00, -1.08));
		add(new Instrument("EZJ", "EASYJET PLC", 623.00, 8775779, 596.00, 628.60, 596.00, + 0.81));
		add(new Instrument("RIO", "RIO TINTO PLC", 4560.50, 5912, 4535.00, 4618.00, 4509.00, -0.77));
		add(new Instrument("TUI", "TUI Group", 245.30, 6796808, 244.70, 249.70, 241.00, -0.69));
		add(new Instrument("LLOY", "Lloyds Banking Group Plc", 50.22, 261909017, 49.83, 50.47, 49.64, +0.64));
		add(new Instrument("GBX", "Barclays Plc", 199.12, 2825794, 199.36, 200.17, 197.48, +0.66));

	}};

	private final String username = "ADMIN";
	@Autowired
	private OrderBookRepository orderBookRep;

	@Autowired
	private InstrumentRepository instrumentRepository;
	
	@Autowired
	private UserRepository userRep;
	
	@Test
	void test() {
		for(String name : instrumentCodes) {
			OrderBook orderBook = new OrderBook(instrumentRepository.findById(name).get());
			for(int i = 0; i < 10; i++) {
				Order randomOrder;
				BuyOrSell buyOrSell;
				OrderType type;
				int shareQuantity = (int) (Math.random() * (1000 -1 + 1) + 1);
				if(Math.floor(Math.random() * (2+1) + 0) == 0) {
					buyOrSell = BuyOrSell.BUY;
				}else {
					buyOrSell = BuyOrSell.SELL;
				}
				if(Math.floor(Math.random() * (3+1) + 0) == 0) {
					//Market
					type = OrderType.MARKET;
					randomOrder = new Order(orderBook, userRep.getUserByUsername(username),buyOrSell, type, shareQuantity);
				}else {
					//Limit
					type = OrderType.LIMIT;
					double limit = (Math.random() * (300 -1 + 1) + 1);
					randomOrder = new Order(orderBook, userRep.getUserByUsername(username),buyOrSell, type, limit, shareQuantity);
				}
				orderBook.getOrders().add(randomOrder);
			}
			orderBookRep.save(orderBook);
		}
	}


	@Test
	void createInstruments() {
		instrumentRepository.saveAll(instrumentsList);
	}
}
