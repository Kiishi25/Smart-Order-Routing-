package com.ab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.Exchange;
import com.ab.entities.Order;
import com.ab.entities.OrderBook;

@Service
public class SortService {
	@Autowired
	private List<OrderBook> orderBooks;
	
	private List<Exchange> exchanges;

	public String executeTrade(Order order) {
		return "";
	}
}
