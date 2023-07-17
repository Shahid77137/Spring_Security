package com.masai.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.CartException;
import com.masai.Model.Cart;
import com.masai.Model.Product;
import com.masai.Model.User;
import com.masai.Repository.CartRepository;
import com.masai.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	public CartService cartservice;
	@Autowired
	public CartRepository cartRepository;
	
	public Cart getCartByUser(User user) throws CartException {
        Optional<Cart> optionalCart = Optional.of(cartRepository.findByUser(user));
        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            throw new CartException("Cart not found");
        }
    }

    public Cart addToCart(User user, Product product) throws CartException {
        Cart cart = getCartByUser(user);
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    public Cart removeFromCart(User user, Product product) throws CartException {
        Cart cart = getCartByUser(user);
        cart.getProducts().remove(product);
        return cartRepository.save(cart);
    }
	
}
