package com.masai.Service;

import java.util.List;

import com.masai.Exception.ProductException;
import com.masai.Model.Product;

public interface ProductService {


	public Product createProduct(Product product) throws ProductException;

	public Product getProductById(Integer productId) throws ProductException;

	public List<Product> getAllProducts() throws ProductException;

	public Product updateProducts(Integer productId, Product product)throws ProductException;			

	public Product deleteProducts(Integer productId) throws ProductException;


	
	
}
