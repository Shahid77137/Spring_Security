package com.masai.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CartException;
import com.masai.Model.Cart;
import com.masai.Model.Product;
import com.masai.Model.User;
import com.masai.Repository.CartRepository;
@Service
public class CartServiceImpl implements CartService{

	@Autowired
	public CartRepository cartrepository;
	
	@Override
	public Cart getCartByUser(User user) throws CartException {

		
		Optional<Cart> cart = Optional.of(cartrepository.findByUser(user));
		
		if(cart.isEmpty()) {
			new CartException("Cart not found");
		}
		
		return cart.get();
		
    }
	@Override
    public Cart addToCart(User user, Product product) throws CartException {
        Cart cart = getCartByUser(user);
        cart.getProducts().add(product);
        return cartrepository.save(cart);
    }
	@Override
    public Cart removeFromCart(User user, Product product) throws CartException {
        Cart cart = getCartByUser(user);
        cart.getProducts().remove(product);
        return cartrepository.save(cart);
    }
	
}
