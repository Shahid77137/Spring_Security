package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.BookException;
import com.masai.Exception.UserException;
import com.masai.Model.Book;
import com.masai.Model.User;
import com.masai.Repository.UserRepository;
import com.masai.Service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
    private UserService UserService;
	@Autowired
	private UserRepository Userrepo;
	
//  public User registerUser(User user)throws UserException;
//	
//	public User getUserByUsername(String username)throws UserException;
//	
//	public User getUserByEmail(String email)throws UserException;
//	
//	public void validateUserCredentials(String username, String email)throws UserException;
	
	
	@PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(User user)throws UserException {
    	
        User Userregister = Userrepo.save(user);
        
        return new ResponseEntity<>(Userregister,HttpStatus.CREATED);
    }
	
    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(String username)throws UserException{
       
            User userexists = UserService.getUserByUsername(username);
            
            if(userexists == null) {
            	throw new UserException("User not found");
            }
            
            return new ResponseEntity<>(userexists,HttpStatus.OK);
       
    }

    
    
    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserByEmail(String email)throws UserException{
       
            User userexists = UserService.getUserByUsername(email);
            
            if(userexists == null) {
            	throw new UserException("User not found");
            }
            
            return new ResponseEntity<>(userexists,HttpStatus.OK);
       
    }
    
//    validateUserCredentials(String username, String email)throws UserException;

	
}
