package com.ab.services;

import java.util.List;
import java.util.Optional;

import com.ab.entities.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findUsersTest() {
        List<User> users = userService.findUsers();

        assertTrue(users.size() > 0);
    }
    
//    @Test
//    void addUser() {
//    	userService.registerUser("TestUser", "TestName", "Test@gmail.com", "test");
//    	assertEquals("",userService.findUser("TestUser").get().toString());
//    }
    
    @Test
    void loginUser() {
    	assertEquals("",userService.loginUser("TestUser", "test"));
    }

    @Test
    void findUser() {
        Optional<User> u = userService.findUser("TestUser");

        assertNotNull(u);
        assertTrue(u.isPresent());
        assertNotNull(u.get());
       // assertEquals(12, u.get().getUserid());
    }

}
