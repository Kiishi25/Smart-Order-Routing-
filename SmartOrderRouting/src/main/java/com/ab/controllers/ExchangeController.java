package com.ab.controllers;

import java.util.List;

import com.ab.entities.Exchange;
import com.ab.services.ExchangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

    @Autowired
	private ExchangeService exchangeService;

	@GetMapping("/exchanges")
	public List<Exchange>getAllExchange(){
		return exchangeService.getExchanges();
	}
    
   
}
