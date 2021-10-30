package com.ab.services;

import java.util.List;
import java.util.Optional;
import com.ab.entities.User;

import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    List<User> findUsers();

    User authenticateUser(String username, String password);

    Optional<User> findUser(String username);

    User registerUser(String name, String email, String username, String password);

}
