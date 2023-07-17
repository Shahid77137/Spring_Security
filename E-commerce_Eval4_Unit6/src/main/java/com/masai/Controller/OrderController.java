package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.OrderException;
import com.masai.Exception.UserException;
import com.masai.Model.Order;
import com.masai.Model.Product;
import com.masai.Model.User;
import com.masai.Service.OrderService;
import com.masai.Service.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	public OrderService orderService;
	
	@PostMapping("/placeorder")
	public ResponseEntity<Order> placeorder(@RequestBody User user, List<Product> prod) throws OrderException{
		
		Order od = orderService.placeorder(user, prod);
		return new ResponseEntity<>(od,HttpStatus.CREATED);
	}
	
	@GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderService.getallOrders();
            return ResponseEntity.ok(orders);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        try {
            Order order = orderService.getOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable int orderId) {
        try {
            orderService.DeleteOrderById(orderId);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete order");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderByUser(@PathVariable Long userId) {
        try {
            User user = UserService.getUserById(userId);
            List<Order> userOrders = orderService.getOrderByUser(user);
            return ResponseEntity.ok(userOrders);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        try {
            Order savedOrder = orderService.saveOrdre(order);
            return ResponseEntity.ok(savedOrder);
        } catch (OrderException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	
}
