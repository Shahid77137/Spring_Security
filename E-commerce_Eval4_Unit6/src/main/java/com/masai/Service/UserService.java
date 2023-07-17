package com.masai.Service;

import com.masai.Exception.UserException;
import com.masai.Model.User;

public interface UserService {

	public User registerUser(User user)throws UserException;
	public String login(String username,String password)throws UserException;
	public User getByUsername(String username)throws UserException;
	public static User getUserById(Long userid)throws UserException {
		// TODO Auto-generated method stub
		return null;
	}
}
