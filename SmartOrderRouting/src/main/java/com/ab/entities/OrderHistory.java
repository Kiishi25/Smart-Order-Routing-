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

}
