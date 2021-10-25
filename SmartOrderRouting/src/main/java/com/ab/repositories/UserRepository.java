package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ab.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	@Query("From User u WHERE u.username =:username")
	User getUserByUsername(@Param("username")String username);

}
