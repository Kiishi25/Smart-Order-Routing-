package com.ab.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ab.entities.enums.BuyOrSell;
import com.ab.entities.enums.OrderStatus;
import com.ab.entities.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "buy_or_sell", columnDefinition = "ENUM('BUY', 'SELL') DEFAULT 'BUY'")
    @Enumerated(EnumType.STRING)
	private BuyOrSell buyOrSell;//buy/sell

    @Column(name = "type", columnDefinition = "ENUM('MARKET', 'LIMIT', 'HIDDEN', 'TIMED') DEFAULT 'MARKET'")
    @Enumerated(EnumType.STRING)
	private OrderType type;//market/priceLimit/

    @Column(name = "status", columnDefinition = "ENUM('PARTIAL', 'FULL') DEFAULT 'PARTIAL'")
    @Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.PARTIAL;//partially filled or fully filled

	private String auctionTime;//Opening/closing auction or null
	private double priceLimit;//max/min value a share will be bought or sold for
	private int shareQuantity;//the amount of shares to be bought or sold
	private boolean isHidden;//if true wont display priceLimit or quantity
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	@OneToMany( fetch =FetchType.EAGER,
	mappedBy = "order",
	cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TradeHistory> history = new ArrayList<TradeHistory>();
	//list showing how many shares this order has taken/given to another order
	
	@ManyToOne
    @JoinColumn(name = "username")
	@JsonBackReference
	private User user;
	
	public Order() {}
	
	//priceLimit Order
	public Order(OrderBook orderBook, User user, BuyOrSell buyOrSell, OrderType type, double priceLimit, int shareQuantity) {
		this.orderBook = orderBook;
		this.user = user;
		this.buyOrSell = buyOrSell;
		this.type = type;
		this.priceLimit = priceLimit;
		this.shareQuantity = shareQuantity;
	}
	//Market Order
	public Order(OrderBook orderBook, User user, BuyOrSell buyOrSell, OrderType type, int shareQuantity) {
		this.orderBook = orderBook;
		this.user = user;
		this.buyOrSell = buyOrSell;
		this.type = type;
		this.shareQuantity = shareQuantity;
	}
	//Hidden Order
	public Order(OrderBook orderBook, User user, BuyOrSell buyOrSell, OrderType type, double priceLimit, int shareQuantity, boolean isHidden) {
		this.orderBook = orderBook;
		this.user = user;
		this.buyOrSell = buyOrSell;
		this.type = type;
		this.priceLimit = priceLimit;
		this.shareQuantity = shareQuantity;
		this.isHidden = isHidden;
	}
	//Open/Close Order
	public Order(OrderBook orderBook, User user, BuyOrSell buyOrSell, OrderType type, double priceLimit, int shareQuantity, String auctionTime) {
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
		return "Order [orderID=" + orderID + ", type=" + type + ", status=" + status
				+ ", priceLimit=" + priceLimit + ", shareQuantity=" + shareQuantity + "]";
	}
	
}
