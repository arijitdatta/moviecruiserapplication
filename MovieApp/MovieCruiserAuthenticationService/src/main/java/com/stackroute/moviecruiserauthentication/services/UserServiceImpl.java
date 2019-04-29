package com.stackroute.moviecruiserauthentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserauthentication.exceptions.UserAlreadyPresentException;
import com.stackroute.moviecruiserauthentication.exceptions.UserNotFoundException;
import com.stackroute.moviecruiserauthentication.model.User;
import com.stackroute.moviecruiserauthentication.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private final UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo=userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyPresentException, UserNotFoundException {
		Optional<User> u1=userRepo.findById(user.getUserId());
		if(u1.isPresent()) {
			throw new UserAlreadyPresentException("User with Id ::"+user.getUserId()+ " is already present.");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user =userRepo.findByUserIdAndPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("UserId and Password mismatch");
		}
		return user;
	}

}
