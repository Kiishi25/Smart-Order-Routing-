package com.ab.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;



@Entity
@Table(name="Users")
@Data
public class User {

	@Id
	private String username;
	
	private String fullName;
	private String email;
	private String password;
	
	@OneToMany( fetch =FetchType.EAGER,
			mappedBy = "user",
			cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders = new ArrayList<Order>();
	
	public User(String name, String email, String username, String password) {
		this.username = username;
		this.fullName = name;
		this.email = email;
		this.password = password;
	}
	
	public User() {}
	
	public String getPassword() {
		return password;
	}
	
	
	
}
