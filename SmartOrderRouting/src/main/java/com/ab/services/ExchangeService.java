package com.ab.services;

import java.util.List;
import java.util.Optional;

import com.ab.entities.Exchange;

import com.ab.repositories.ExchangeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
	private ExchangeRepository exchangeRepo;


    public List<Exchange> getExchanges() {
        return exchangeRepo.findAll();
    }

    public Optional<Exchange> findExchange(int exchangeId) {
        return exchangeRepo.findById(exchangeId);
    }
    
}
