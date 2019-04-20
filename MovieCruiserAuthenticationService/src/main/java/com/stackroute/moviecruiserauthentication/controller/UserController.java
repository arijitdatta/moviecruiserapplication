package com.stackroute.moviecruiserauthentication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.moviecruiserauthentication.model.User;
import com.stackroute.moviecruiserauthentication.services.SecurityTokenGenerator;
import com.stackroute.moviecruiserauthentication.services.UserService;

@RestController
@EnableWebMvc
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityTokenGenerator tokenGenerator;

	public UserController(UserService userService, SecurityTokenGenerator tokenGenerator) {
		super();
		this.userService = userService;
		this.tokenGenerator = tokenGenerator;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered sucessfully.", HttpStatus.CREATED);
			
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetail){
		try {
			String userId=loginDetail.getUserId();
			String password=loginDetail.getPassword();
			if(userId==null || password==null) {
				throw new Exception("UserID and Password cannot be empty.");
			}
			
			User user=userService.findByUserIdAndPassword(userId, password);
			if(user==null) {
				throw new Exception ("User with given ID is not present.");
			}
			if(user.getPassword().equals(password)==false){
				throw new Exception ("invalid login credential. Please chek username & password");
			}
			Map<String, String> tokenMap=tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String,String>>(tokenMap, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

}
