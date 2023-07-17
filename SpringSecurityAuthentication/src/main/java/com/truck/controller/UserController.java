package com.truck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.truck.Exception.UserException;
import com.truck.model.User;
import com.truck.service.UserService;

@RestController
public class UserController {

	
	@Autowired
	private UserService us;
	
	@Autowired
	private PasswordEncoder ps;
	
	
	@PostMapping("/users")
	public ResponseEntity<User> saveCustomerHandler(@RequestBody User user) throws UserException{

		
		user.setPassword(ps.encode(user.getPassword()));
		
		User registeredCustomer= us.registerUser(user);
		
		return new ResponseEntity<>(registeredCustomer,HttpStatus.ACCEPTED);
		
	}
	
	 @GetMapping("/welcome")
	    public ResponseEntity<String> welcome() {
	        return ResponseEntity.ok("Welcome!");
	    }

	
	
	
	
	
	
}
