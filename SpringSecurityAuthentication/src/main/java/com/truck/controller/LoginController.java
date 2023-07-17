package com.truck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truck.Repo.UserRepo;
import com.truck.model.User;

@RestController
public class LoginController {

	@Autowired
	private UserRepo ur;
	
	@GetMapping("/signIn")
	public ResponseEntity<User> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		System.out.println(auth);
		
		 User user= ((Object) ur.findByEmail(auth.getName())).orElseThrow(() -> new InvalidCredentialsException("Invalid Username or password"));
		
		
		 
		 
		 return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		
		
	}
	
}
