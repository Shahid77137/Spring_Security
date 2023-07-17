package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.User;
import com.masai.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	/**
	 * Creates a new user and adds it to the database.
	 *
	 * @param user The user object containing the details of the user to be created.
	 * @return The created user object.
	 * @throws UserException If an error occurs while creating the user.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public User createUser(User user) throws UserException {

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserException("Username is Already taken");
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserException("Email is already taken , try with valid Email Id");
		}

		return userRepository.save(user);
	}
	
	/**
	 * Logs in a user with the specified username and password.
	 *
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return The authentication token for the logged-in user.
	 * @throws UserNotFoundException If the user with the specified username is not found.
	 * 
	 * @author HoshiyarJyani
	 */
	@Override
	public String loginUser(String username, String password) throws UserNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			double randomNum = Math.random();
			return "token" + randomNum*1000000+"xjdfksjzcxc";
		} else {
			throw new UserNotFoundException("UserName or Password Invalid");
		}

	}

}
