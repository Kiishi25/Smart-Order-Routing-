package com.ab.controllers;

import java.util.List;

import com.ab.entities.TradeHistory;
import com.ab.services.TradeHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class TradeHistoryController {
   
	@Autowired
	private TradeHistoryService historyService;

	@GetMapping("/{username}")
	public List<TradeHistory> getAllHistory(@PathVariable String username){
		return historyService.findTradeHistoryByUsername(username);
	}
}
