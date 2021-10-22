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
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userID;
	
	private String username;
	private String fullName;
	private String email;
	private String password;
	
	@OneToMany( fetch =FetchType.EAGER,
			mappedBy = "user",
			cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	public User(String username, String fullName, String email, String password) {
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}
	
	public User() {}


	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	

	@Override
	public String toString() {
		return "{" +
			" userID='" + getUserID() + "'" +
			", username='" + getUsername() + "'" +
			", fullName='" + getFullName() + "'" +
			", email='" + getEmail() + "'" +
			", password='" + getPassword() + "'" +
			", orders='" + getOrders() + "'" +
			"}";
	}
	
	
	
	
}
