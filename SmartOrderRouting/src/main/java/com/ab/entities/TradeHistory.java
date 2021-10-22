package com.ab.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;



@Entity
@Table(name="TradeHistory")
public class TradeHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private int historyID;
	
	
	@ManyToOne
    @JoinColumn(name = "orderID1")
	private Order order;
	
	private int orderID2;
	private int shareQuantity;
	private double value;
	
	public TradeHistory(Order order,int orderID2,int shareQuantity, double value) {
		this.order = order;
		this.orderID2 = orderID2;
		this.shareQuantity = shareQuantity;
		this.value = value;
	}
	
	public TradeHistory() {}

	@Override
	public String toString() {
		return "TradeHistory [historyID=" + historyID + ", order=" + order + ", orderID2=" + orderID2
				+ ", shareQuantity=" + shareQuantity + ", value=" + value + "]";
	}

	public int getHistoryID() {
		return historyID;
	}

	public Order getOrder() {
		return order;
	}

	public int getOrderID2() {
		return orderID2;
	}

	public int getShareQuantity() {
		return shareQuantity;
	}

	public double getValue() {
		return value;
	}

	
}
