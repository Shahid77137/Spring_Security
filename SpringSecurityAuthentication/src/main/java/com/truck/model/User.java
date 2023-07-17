package com.truck.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.NoArgsConstructor;



@Entity

@NoArgsConstructor

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private	Long id;
	
	private String name;
	
	private String password;
	
	private Set<Role> role;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public User(String name, String password, Set<Role> role) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
	}

	
	
	
}

