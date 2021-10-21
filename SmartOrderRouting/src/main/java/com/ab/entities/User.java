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
	private String firstname;
	private String lastname;
	private String password;
	
	@OneToMany( fetch =FetchType.EAGER,
			mappedBy = "user",
			cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	public User(String username, String firstname, String lastname, String password) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}
	
	public User() {}
	
	
	
	
}
