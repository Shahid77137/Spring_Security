package com.truck.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truck.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Object findByEmail(String name);

}
