package com.ab.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="OrderBooks")
public class OrderBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderBookID;
	
	private String instrumentName;
	
	@OneToMany( fetch =FetchType.EAGER,
	mappedBy = "orderBook",
	cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<Order>();
	
	
}