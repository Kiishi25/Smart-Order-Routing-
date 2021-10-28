package com.ab.helpers;

import com.ab.entities.Order;
import com.ab.entities.OrderBook;

public class DatabasePopulator {
	private final static String[] instrumentNames = {
			"IAG", "SMT", "BOO", "THG", "ARB",
			"ASC", "EZJ", "RIO", "TUI", "LLOY"};
	
	public static void main(String[] args) {
		for(String name : instrumentNames) {
			OrderBook orderBook = new OrderBook(name);
			for(int i = 0; i < 10; i++) {
				if(Math.floor(Math.random() * (3+1) + 0) == 0) {
					//Market
				}else {
					//Limit
				}
				Order randomOrder = new Order();
			}
		}
	}

}
