package com.masai.Service;



import com.masai.Exception.CartException;
import com.masai.Model.Cart;
import com.masai.Model.Product;
import com.masai.Model.User;

public interface CartService {

	public Cart getCartByUser(User user) throws CartException;
	public Cart addToCart(User user, Product product) throws CartException;
	public Cart removeFromCart(User user, Product product) throws CartException;
	
}
