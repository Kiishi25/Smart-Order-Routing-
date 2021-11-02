package com.ab.controllers;

import java.util.List;

import com.ab.entities.OrderBook;
import com.ab.services.OrderBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin
@RestController
@RequestMapping("/orderBook")
public class OrderBookController {
   
	@Autowired
	private OrderBookService orderService;

	@GetMapping
	public List<OrderBook> getAllOrderBooks(){
		return orderService.findOrderBooks();
	}
	
	@PostMapping
	public OrderBook create(@RequestBody OrderBook req){
		return orderService.create(req);
	}
}
