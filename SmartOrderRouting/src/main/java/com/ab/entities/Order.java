package com.ab.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderID;
	
	@ManyToOne
    @JoinColumn(name = "orderBookID")
	private OrderBook orderBook;
	
	private String type;//buy/sell
	private String status;//partially filled or fully filled
	private double priceLimit;//max/min value a share will be bought or sold for
	private int shareQuantity;//the amount of shares to be bought or sold
	
	@OneToMany( fetch =FetchType.EAGER,
	mappedBy = "order",
	cascade = CascadeType.ALL)
	//				orderID shareQuantity
	private List<OrderHistory> history = new ArrayList<OrderHistory>();
	//list showing how many shares this order has taken/given to another order
	
	@ManyToOne
    @JoinColumn(name = "userID")
	private User user;
	
	public Order() {}
	
	public Order(String type, double limit, int shareQuantity) {
		this.type = type;
		this.priceLimit = limit;
		this.shareQuantity = shareQuantity;
	}
	
	public int getOrderID() {
		return orderID;
	}
	
	public double getPriceLimit() {
		return priceLimit;
	}
	
	public int getShareQuantity() {
		return shareQuantity;
	}
	
	public List<OrderHistory> getHistory(){
		return history;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderBook=" + orderBook + ", type=" + type + ", status=" + status
				+ ", priceLimit=" + priceLimit + ", shareQuantity=" + shareQuantity + ", history=" + history + "]";
	}
	
}
