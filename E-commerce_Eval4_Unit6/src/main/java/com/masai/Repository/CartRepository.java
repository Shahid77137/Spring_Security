package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.Cart;
import com.masai.Model.User;

public interface CartRepository extends JpaRepository<Cart, Long>{

	public Cart findByUser(User user); 
	
}
