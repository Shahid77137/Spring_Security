package com.masai.Service;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.masai.Exception.UserException;
import com.masai.Model.User;
import com.masai.Repository.UserRepository;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
	public UserRepository userrepository;
	
	@Override
	public User registerUser(User user) throws UserException {
		
		if (userrepository.existsByUsername(user.getUserName())) {
			throw new UserException("Username is Already taken");
		}

		if (userrepository.existsByEmail(user.getEmail())) {
			throw new UserException("Email is already taken , try with valid Email Id");
		}

		return userrepository.save(user);

	}

	@Override
	public String login(String username, String password) throws UserException {
	
		User user = userrepository.findByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			double randomNum = Math.random();
			return "token" + randomNum*1000000+"xjdfksjzcxc";
		} else {
			throw new UserException("UserName or Password Invalid");
		}
		
	}

	@Override
	public User getByUsername(String username) throws UserException {
		
//		User us = userrepository.findByUsername(username);
		Optional<User> use = Optional.ofNullable(userrepository.findByUsername(username));
		
		if(use.isEmpty()) {
			throw new UserException("User not found by this name : "+username);
		}
		
		return use.get();
	}

//	@Override
//	public User getUserById(Long userId) throws UserException {
//        Optional<User> optionalUser = userrepository.findById(userId);
//        if (optionalUser.isPresent()) {
//            return optionalUser.get();
//        } else {
//            throw new UserException("User not found");
//        }
//    }
}
