package com.masai.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BookException;
import com.masai.Exception.UserException;
import com.masai.Model.Book;
import com.masai.Model.User;
import com.masai.Repository.BookRepository;
import com.masai.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	public BookRepository bookRepository;
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public User registerUser(User user) throws UserException {
		// TODO Auto-generated method stub
		
		if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getUserName().isEmpty()) {
            throw new IllegalArgumentException("username cannot be empty");
        }
        if (user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
   
        return userRepository.save(user);

	}

	@Override
	public User getUserByUsername(String username) throws UserException {
		// TODO Auto-generated method stub
         Optional<User> user =  userRepository.findByUsername(username);
		
		if(user.isPresent()) {
			
			User u = user.get();
			return u;
			
		}else {
			throw new UserException("No User Found");
		}

	}

	@Override
	public User getUserByEmail(String email) throws UserException {
		// TODO Auto-generated method stub
		
		 Optional<User> user =  userRepository.findByEmail(email);
			
			if(user.isPresent()) {
				
				User u = user.get();
				return u;
				
			}else {
				throw new UserException("No User Found");
			}

	}

	@Override
	public void validateUserCredentials(String username, String email) throws UserException {
		// TODO Auto-generated method stub
		
	        if (userRepository.findByUsername(username).isPresent()) {
	            throw new IllegalArgumentException("Username already exists with " + username +" this username");
	        }

	        if (userRepository.findByEmail(email).isPresent()) {
	            throw new IllegalArgumentException("Email already exists with " + email +" this email");
	        }
	    
	}

}
