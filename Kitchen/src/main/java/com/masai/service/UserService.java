package com.masai.service;

import com.masai.exception.UserException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.User;

/**
 * 
 * The UserService interface defines the contract for interacting with the
 * user-related functionality. This interface provides methods for
 * adding a user to the database and logging in a user. The implementation of
 * this interface should handle any exceptions related to user operations.
 * 
 * @author HoshiyarJyani
 *
 */
public interface UserService {
	/**
	 * Creates a new user and adds it to the database.
	 *
	 * @param user The user object containing the details of the user to be created.
	 * @return The created user object.
	 * @throws UserException If an error occurs while creating the user.
	 * 
	 * @author HoshiyarJyani
	 */
	public User createUser(User user) throws UserException;

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
	public String loginUser(String username, String password) throws UserNotFoundException;
}
