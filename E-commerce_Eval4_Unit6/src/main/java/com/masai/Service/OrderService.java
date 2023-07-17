package com.masai.Service;

import java.util.List;
import java.util.Set;

import com.masai.Exception.OrderException;
import com.masai.Model.Order;
import com.masai.Model.Product;
import com.masai.Model.User;

public interface OrderService {

	public Order placeorder(User user, List<Product> prod) throws OrderException;
	public List<Order> getallOrders()throws OrderException;
	public Order getOrderById(int orderid)throws OrderException;
	public String DeleteOrderById(int orderId)throws OrderException;
	public List<Order> getOrderByUser(User user);
	public Order saveOrdre(Order order)throws OrderException;
}
