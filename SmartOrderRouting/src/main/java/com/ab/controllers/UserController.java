package com.ab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.ab.entities.User;
import com.ab.services.IUserService;


@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
    User loginUser(@Validated(User.class) @RequestBody User user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        User resUser = userService.authenticateUser(user.getUsername(), user.getPassword());

        if (resUser != null) {
            System.out.println("User: " + resUser.getUsername() + " have " + resUser.getOrders().size() + " orders.");
        } else {
            System.out.println("User not found.");
        }

        return resUser;
    }

    @GetMapping("/findUsers")
    List<User> findUsers() {
        return userService.findUsers();
    }

    @GetMapping("/findUser/{userId}")
    Optional<User> findUser(@PathVariable int userId) {
        return userService.findUser(userId);
    }

}
