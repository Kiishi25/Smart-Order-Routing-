package com.ab.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.ab.entities.User;
import com.ab.helpers.PasswordEncryptor;
import com.ab.repositories.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRep;
	
	public void registerUser(String username, String firstname, String lastname, String password) {
		password = PasswordEncryptor.encrypt(password);
		User user = new User(username,firstname,lastname,password);
		userRep.save(user);
	}
	
	public User loginUser(String username, String password) {
		User user = userRep.getUserByUsername(username);
		String storedPassword = PasswordEncryptor.decrypt(user.getPassword());
		if(password.equals(storedPassword)) {
			return user;
		}else {
			return null;
		}
	}
}
