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
public class TradeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private int historyID;
	
	
	@ManyToOne
    @JoinColumn(name = "orderID")
	private Order order;
	
	@ManyToOne
    @JoinColumn(name = "orderTradingWithID")
	private Order orderTradingWith;

	private int shareQuantity;
	private double value;
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	public TradeHistory(Order order,Order orderTradingWith,int shareQuantity, double value) {
		this.order = order;
		this.orderTradingWith = orderTradingWith;
		this.shareQuantity = shareQuantity;
		this.value = value;
	}
	
	public TradeHistory() {}

	@Override
	public String toString() {
		return "History [historyID=" + historyID + ", order=" + order + ", orderTradingWith=" + orderTradingWith
				+ ", shareQuantity=" + shareQuantity + ", value=" + value + "]";
	}
	
}
