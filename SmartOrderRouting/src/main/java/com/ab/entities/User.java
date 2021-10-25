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

import lombok.Getter;

@Entity
@Table(name="Users")
public class User {

	@Id
	@Getter
	private String username;
	@Getter
	private String fullName;
	@Getter
	private String email;
	@Getter
	private String password;
	
	@OneToMany( fetch =FetchType.EAGER,
			mappedBy = "user",
			cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	public User(String username, String name, String email, String password) {
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
