package com.stackroute.moviecruiserauthentication.services;

import java.util.Map;

import com.stackroute.moviecruiserauthentication.model.User;

public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(User user);

}
