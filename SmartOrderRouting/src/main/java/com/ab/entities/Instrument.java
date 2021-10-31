package com.ab.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Entity
@Table(name="Instruments")
@Data
public class Instrument {

	@Id
	private String code;

	private String company;
    private double price;
	private int volume;
	private double open;
	private double high;
	private double low;
	private double priceChange;

	public Instrument(String code, String company, double price, int volume, double open, double high, double low, double priceChange) {
		this.code = code;
		this.company = company;
		this.price = price;
		this.volume = volume;
		this.open = open;
		this.high = high;
		this.low = low;
		this.priceChange = priceChange;
	}

	public Instrument(){

	}
	
	
}
