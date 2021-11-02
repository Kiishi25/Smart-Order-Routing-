package com.ab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.ab.entities.Order;
import com.ab.entities.OrderBook;
import com.ab.models.Action;
import com.ab.repositories.OrderBookRepository;

@Service
public class OrderBookService {

	@Autowired
	private OrderBookRepository repo;
	
    public List<OrderBook> findOrderBooks() {
        return repo.findAll();
    }
    
    public OrderBook findOrderBook(String instrumentName) {
    	return repo.getByInstrumentName(instrumentName);
    }

	
}