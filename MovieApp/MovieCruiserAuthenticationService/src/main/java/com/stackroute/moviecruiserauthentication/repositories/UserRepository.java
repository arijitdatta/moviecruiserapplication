package com.stackroute.moviecruiserauthentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stackroute.moviecruiserauthentication.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	/*@Query("Select u from User u where userId=(?1) and password=(?2)")
	User validate(String userID, String password);*/
	
	User findByUserIdAndPassword(String userId, String password);

}
