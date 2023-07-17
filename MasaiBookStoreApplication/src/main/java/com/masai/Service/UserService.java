package com.masai.Service;

import com.masai.Exception.UserException;
import com.masai.Model.User;

public interface UserService {

	public User registerUser(User user)throws UserException;
	
	public User getUserByUsername(String username)throws UserException;
	
	public User getUserByEmail(String email)throws UserException;
	
	public void validateUserCredentials(String username, String email)throws UserException;
	
}
