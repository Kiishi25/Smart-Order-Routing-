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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name="OrderBooks")
@Data
public class OrderBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderBookID;
	
	@OneToOne
    @JoinColumn(name = "code")
	private Instrument instrument;
	
	@OneToMany( fetch =FetchType.LAZY,
	mappedBy = "orderBook",
	cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders = new ArrayList<Order>();
	
	public OrderBook(){}

	public OrderBook(Instrument instrument) {
		this.instrument = instrument;
	}
}
