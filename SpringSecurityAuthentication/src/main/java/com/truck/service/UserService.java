package com.truck.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truck.Exception.UserException;
import com.truck.Repo.TruckRepo;
import com.truck.Repo.UserRepo;
import com.truck.model.User;

@Service
public class UserService {

	
	@Autowired
	private UserRepo ur;
	
	@Autowired
	private TruckRepo tr;
	
	
	public User registerUser(User user) throws UserException{
		
	return ur.save(user);
		
	}
	
	
	
}
