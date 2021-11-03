package com.ab.controllers;

import java.util.List;

import com.ab.entities.TradeHistory;
import com.ab.services.TradeHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class TradeHistoryController {
   
	@Autowired
	private TradeHistoryService historyService;

	@GetMapping
	public List<TradeHistory> getAllHistory(){
		System.out.println(historyService.findTradeHistory().get(0).getOrder());
		return historyService.findTradeHistory();

	}
}
