package com.ab.services;

import java.util.List;
import java.util.Optional;
import com.ab.entities.User;

public interface IUserService {

    List<User> findUsers();

    User authenticateUser(String username, String password);

    Optional<User> findUser(int userId);

}
