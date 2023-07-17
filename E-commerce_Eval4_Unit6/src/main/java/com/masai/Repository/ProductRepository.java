package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
