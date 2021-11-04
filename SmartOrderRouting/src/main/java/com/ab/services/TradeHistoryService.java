package com.ab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.ab.entities.TradeHistory;
import com.ab.repositories.TradeHistoryRepository;

@Service
public class TradeHistoryService {

	@Autowired
	private TradeHistoryRepository repo;
	
    public List<TradeHistory> findTradeHistory() {
        return repo.findAll();
    }

    public List<TradeHistory> findTradeHistoryByUsername(String username) {
        return repo.findAllByUsername(username);
    }
}