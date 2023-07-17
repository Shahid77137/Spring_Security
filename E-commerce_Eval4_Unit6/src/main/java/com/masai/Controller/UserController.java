package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.UserException;
import com.masai.Model.User;
import com.masai.Service.UserService;

@RestController
@RequestMapping("/Users")
public class UserController {

	@Autowired
	public UserService userService;
	
	@PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            String token = userService.login(username, password);
            return ResponseEntity.ok(token);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        try {
            User user = userService.getByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	
}
