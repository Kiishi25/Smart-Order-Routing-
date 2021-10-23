package com.ab.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ab.entities.User;
import com.ab.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@Transactional
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User authenticateUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public Optional<User> findUser(int userId) {
        return userRepository.findById(userId);
    }
    
}
