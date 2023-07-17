package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
