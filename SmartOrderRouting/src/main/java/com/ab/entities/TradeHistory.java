package com.ab.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.Getter;



@Entity
@Table(name="TradeHistory")
@Data
public class TradeHistory{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private int historyID;
	
	
	@ManyToOne
    @JoinColumn(name = "orderID1")
	// @JsonBackReference
	private Order order;
	
	private int orderTradingWithID;
	private int shareQuantity;
	private double value;
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	public TradeHistory(Order order,int orderTradingWithID,int shareQuantity, double value) {
		this.order = order;
		this.orderTradingWithID = orderTradingWithID;
		this.shareQuantity = shareQuantity;
		this.value = value;
	}
	
	public TradeHistory() {}

	@Override
	public String toString() {
		return "TradeHistory [historyID=" + historyID + ", order=" + order + ", orderTradingWithID=" + orderTradingWithID
				+ ", shareQuantity=" + shareQuantity + ", value=" + value + "]";
	}
	
}
