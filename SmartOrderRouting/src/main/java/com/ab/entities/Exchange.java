package com.ab.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Exchanges")
@Data
public class Exchange {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int exchangeId;
    
    private double feeLadder;
	private double currentDaysTotalTradeValue;
	
	@OneToMany(fetch =FetchType.EAGER)
	private List<OrderBook> orderBooks = new ArrayList<OrderBook>();
	
	public Exchange(){}

	public Exchange(int exchangeId, double feeLadder, double currentDaysTotalTradeValue) {
        this.exchangeId = exchangeId;
		this.feeLadder = feeLadder;
        this.currentDaysTotalTradeValue = currentDaysTotalTradeValue;
	}
	
    
}
