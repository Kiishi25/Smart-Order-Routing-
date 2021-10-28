package com.ab.entities;


import java.time.LocalDateTime;
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

import com.ab.models.Action;
import com.ab.models.OrderType;

import lombok.Data;


@Entity
@Table(name="Orders")
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderID;
	
	@ManyToOne
    @JoinColumn(name = "orderBookID")
	private OrderBook orderBook;
	private Action buyOrSell;//buy/sell
	private OrderType type;//market/priceLimit/
	private String status = "partial";//partially filled or fully filled
	private String auctionTime;//Opening/closing auction or null
	private double priceLimit;//max/min value a share will be bought or sold for
	private int shareQuantity;//the amount of shares to be bought or sold
	private boolean isHidden;//if true wont display priceLimit or quantity
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	@OneToMany( fetch =FetchType.EAGER,
	mappedBy = "order",
	cascade = CascadeType.ALL)
	private List<TradeHistory> history = new ArrayList<TradeHistory>();
	//list showing how many shares this order has taken/given to another order
	
	@ManyToOne
    @JoinColumn(name = "userid")
	private User user;
	
	public Order() {}
	//priceLimit Order
	public Order(OrderBook orderBook, User user, Action buyOrSell, OrderType type, double priceLimit, int shareQuantity) {
		this.orderBook = orderBook;
		this.user = user;
		this.buyOrSell = buyOrSell;
		this.type = type;
		this.priceLimit = priceLimit;
		this.shareQuantity = shareQuantity;
	}
	//Market Order
	public Order(OrderBook orderBook, User user, Action buyOrSell, OrderType type, int shareQuantity) {
		this.orderBook = orderBook;
		this.user = user;
		this.buyOrSell = buyOrSell;
		this.type = type;
		this.shareQuantity = shareQuantity;
	}
	//Hidden Order
	public Order(OrderBook orderBook, User user, Action buyOrSell, OrderType type, double priceLimit, int shareQuantity, boolean isHidden) {
		this.orderBook = orderBook;
		this.user = user;
		this.buyOrSell = buyOrSell;
		this.type = type;
		this.priceLimit = priceLimit;
		this.shareQuantity = shareQuantity;
		this.isHidden = isHidden;
	}
	//Open/Close Order
	public Order(OrderBook orderBook, User user, Action buyOrSell, OrderType type, double priceLimit, int shareQuantity, String auctionTime) {
		this.orderBook = orderBook;
		this.user = user;
		this.buyOrSell = buyOrSell;
		this.type = type;
		this.priceLimit = priceLimit;
		this.shareQuantity = shareQuantity;
		this.auctionTime = auctionTime;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderBook=" + orderBook + ", type=" + type + ", status=" + status
				+ ", priceLimit=" + priceLimit + ", shareQuantity=" + shareQuantity + ", history=" + history + "]";
	}


	
}
