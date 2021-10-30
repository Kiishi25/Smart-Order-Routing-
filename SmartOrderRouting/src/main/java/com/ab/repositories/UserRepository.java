package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

    @Query("SELECT u from User u where u.username = :username and u.password = :password")
    public User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
