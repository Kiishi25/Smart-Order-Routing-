package com.ab.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Exchanges")
@Data
public class Exchange {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int exchangeId;
    
    private double feelLedder;
	private double currentDaysTotalTradeValue;
	
	// @OneToMany(fetch = FetchType.EAGER)
	// private List<OrderBook> orderBooks = new ArrayList<OrderBook>();
	
	public Exchange(){}

	public Exchange(int exchangeId, double feelLedder, double currentDaysTotalTradeValue) {
        this.exchangeId = exchangeId;
		this.feelLedder = feelLedder;
        this.currentDaysTotalTradeValue = currentDaysTotalTradeValue;
	}
	
    
}
