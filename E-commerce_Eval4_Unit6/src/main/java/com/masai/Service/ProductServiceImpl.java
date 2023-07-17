package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.ProductException;
import com.masai.Model.Product;
import com.masai.Repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	public ProductRepository productrepository;
	
	@Override
	public Product createProduct(Product product) throws ProductException {
		
		
			return productrepository.save(product);
		

	}

	@Override
	public Product getProductById(Integer productId) throws ProductException {
		
		Optional<Product> product = productrepository.findById(productId);
		if (product.isEmpty()) {
			throw new ProductException("product not found with ID is " + productId);
		}
		return product.get();

	}

	@Override
	public List<Product> getAllProducts() throws ProductException {
		
		List<Product> list = productrepository.findAll();
		if (list.size() == 0) {
			throw new ProductException("Product not found");
		} else {
			return list;
		}
		

	}

	@Override
	public Product updateProducts(Integer productId, Product product) throws ProductException {
		
		Optional<Product> prod = productrepository.findById(productId);
		if (prod.isEmpty()) {
			throw new ProductException("Product not found wiht ID " + productId);
		}

	

		Product epro = prod.get();
		epro.setProductName(product.getProductName());
		epro.setDescription(product.getDescription());
		epro.setPrice(product.getPrice());
		
		return productrepository.save(epro);

	}

	@Override
	public Product deleteProducts(Integer productId) throws ProductException {
		

		Optional<Product> prod = productrepository.findById(productId);
		if (prod.isEmpty()) {
			throw new ProductException(
					"Product not found with ID " + productId);
		}

		Product rec = prod.get();

		productrepository.delete(rec);
		return rec;
	}

}
