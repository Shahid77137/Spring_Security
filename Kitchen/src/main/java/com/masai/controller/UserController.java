package com.masai.controller;

import com.masai.exception.UserException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.User;
import com.masai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The UserController class handles the HTTP requests related to user
 * operations. It exposes endpoints for creating a user and logging in a user.
 *
 * @author HoshiyarJyani
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Creates a new user.
	 *
	 * @param user The user object to be created.
	 * @return The created user.
	 * @throws UserException If there is an error while creating the user.
	 */
	@PostMapping
	public User createUser(@RequestBody User user) throws UserException {
		return userService.createUser(user);
	}

	/**
	 * Logs in a user.
	 *
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @return A token string representing the logged-in user.
	 * @throws UserNotFoundException If the username or password is invalid.
	 */
	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password) throws UserNotFoundException {
		return userService.loginUser(username, password);
	}

}
