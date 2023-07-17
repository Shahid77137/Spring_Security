package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.Model.Order;
import com.masai.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	@Query("SELECT U FROM USER U WHERE U.USERNAME = ?1")
	public User findByUsername(String username);
	
	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);
	
	public List<Order> findByUser(User user);
	
}
