package com.masai.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.OrderException;
import com.masai.Model.Order;
import com.masai.Model.Product;
import com.masai.Model.User;
import com.masai.Repository.OrderRepository;
import com.masai.Repository.ProductRepository;
import com.masai.Repository.UserRepository;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	public OrderRepository orderrepository;
	
	@Autowired
	public UserRepository userrepository;
	
	@Autowired
	public ProductRepository productrepository;
	
	
	
	@Override
	public Order placeorder(User user, List<Product> prod) throws OrderException {
		
		Order order = new Order();
        order.setUser(user);
        order.setProducts(prod);
        return orderrepository.save(order);
		

	}

	@Override
	public List<Order> getallOrders() throws OrderException {

		return orderrepository.findAll();
	}

	@Override
	public Order getOrderById(int orderid) throws OrderException {
		return orderrepository.findById(orderid)
                .orElseThrow(() -> new OrderException("Order not found"));
	}

	@Override
	public String DeleteOrderById(int orderId) throws OrderException {
		orderrepository.deleteById(orderId);
		return "Order deleted successfully";
	}

	@Override
	public List<Order> getOrderByUser(User user) {
		return userrepository.findByUser(user);

	}

	@Override
	public Order saveOrdre(Order order) throws OrderException {
		return orderrepository.save(order);

	}

}
