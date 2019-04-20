package com.stackroute.moviecruiserauthentication.services;

import com.stackroute.moviecruiserauthentication.exceptions.UserAlreadyPresentException;
import com.stackroute.moviecruiserauthentication.exceptions.UserNotFoundException;
import com.stackroute.moviecruiserauthentication.model.User;

public interface UserService {
	
	boolean saveUser(User user) throws UserAlreadyPresentException, UserNotFoundException;
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
	

}
