package com.ab.entities;

import java.io.Serializable;
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

import lombok.Data;


@Entity
@Table(name="Users")
@Data
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	private String username;
	private String fullName;
	private String email;
	private String password;
	
	@OneToMany( fetch =FetchType.EAGER,
			mappedBy = "user",
			cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<Order>();
	
	public User(String name, String email, String username, String password) {
		this.username = username;
		this.fullName = name;
		this.email = email;
		this.password = password;
	}
	
}
