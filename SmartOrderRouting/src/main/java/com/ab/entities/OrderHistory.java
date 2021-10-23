package com.ab.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="OrderHistory")
public class OrderHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int historyID;
	
	
	@ManyToOne
    @JoinColumn(name = "orderID1")
	private Order order;
	
	private int orderID2;
	private int shareQuantity;
	private double value;
	
	public OrderHistory(Order order,int orderID2,int shareQuantity, double value) {
		this.order = order;
		this.orderID2 = orderID2;
		this.shareQuantity = shareQuantity;
		this.value = value;
	}
	
	public OrderHistory() {}

	@Override
	public String toString() {
		return "OrderHistory [historyID=" + historyID + ", order=" + order + ", orderID2=" + orderID2
				+ ", shareQuantity=" + shareQuantity + ", value=" + value + "]";
	}

	public int getHistoryID() {
		return this.historyID;
	}

	public void setHistoryID(int historyID) {
		this.historyID = historyID;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getOrderID2() {
		return this.orderID2;
	}

	public void setOrderID2(int orderID2) {
		this.orderID2 = orderID2;
	}

	public int getShareQuantity() {
		return this.shareQuantity;
	}

	public void setShareQuantity(int shareQuantity) {
		this.shareQuantity = shareQuantity;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}


}
