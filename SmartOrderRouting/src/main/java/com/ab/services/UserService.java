package com.ab.services;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.ab.entities.User;
import com.ab.helpers.EmailValidator;
import com.ab.helpers.PasswordEncryptor;
import com.ab.repositories.UserRepository;

@Service(value = "database")
public class UserService implements IUserService {
	private static final Logger logger = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository userRep;
	
	public UserService() {
		BasicConfigurator.configure();
	}
	
	@Override
    public List<User> findUsers() {
        return userRep.findAll();
    }

    @Override
    public User authenticateUser(String username, String password) {
		password = PasswordEncryptor.encrypt(password);
        return userRep.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public Optional<User> findUser(String username) {
        return userRep.findById(username);
    }

	public User registerUser(String username, String name, String email, String password) {
		password = PasswordEncryptor.encrypt(password);
		if(EmailValidator.isValid(email)) {
			User user = new User(username,name,email,password);
			userRep.save(user);
			logger.info("Registered new user");

			return user;
		}

		return null;
	}
	
	public User loginUser(String username, String password) {
		User user = userRep.getUserByUsername(username);
		String storedPassword = PasswordEncryptor.decrypt(user.getPassword());
		if(password.equals(storedPassword)) {
			logger.info("User: " + username + " successfully logged in");
			return user;
		}else {
			logger.info("Incorrect password entered");
			return null;
		}
	}
}
