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
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity
@Table(name="OrderBooks")
@Data
public class OrderBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderBookID;
	
	private String instrumentName;
	
	private double marketValue;
	
	@OneToMany( fetch =FetchType.EAGER,
	mappedBy = "orderBook",
	cascade = CascadeType.ALL)
	@JsonManagedReference             // Avoid infinite recurrence when serializing entities  
	private List<Order> orders = new ArrayList<Order>();

    // @ManyToOne
	// @JoinColumn(name = "exchange_id")
	// private Exchange exchange;
	
	public OrderBook(){}

	public OrderBook(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	
}
