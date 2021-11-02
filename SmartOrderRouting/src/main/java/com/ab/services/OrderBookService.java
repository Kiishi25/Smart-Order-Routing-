package com.ab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.ab.entities.OrderBook;
import com.ab.repositories.OrderBookRepository;

@Service
public class OrderBookService {

	@Autowired
	private OrderBookRepository repo;
	
    public List<OrderBook> findOrderBooks() {
        return repo.findAll();
    }
    
    public OrderBook findOrderBook(String instrumentCode) {
    	return repo.getByInstrumentCode(instrumentCode);
    }
    
    public OrderBook create(OrderBook body) {
        if (findOrderBook(body.getInstrumentCode()) == null) return repo.save(body);
        else return findOrderBook(body.getInstrumentCode());
    }
}