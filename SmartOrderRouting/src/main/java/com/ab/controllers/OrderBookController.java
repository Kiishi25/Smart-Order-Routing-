package com.ab.controllers;

import java.util.List;

import com.ab.entities.OrderBook;
import com.ab.services.OrderBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderBookController {
   
	@Autowired
	private OrderBookService orderService;

	@GetMapping("/orderBook")
	public List<OrderBook>getAllOrderBooks(){
		return orderService.findOrderBooks();

	}
}