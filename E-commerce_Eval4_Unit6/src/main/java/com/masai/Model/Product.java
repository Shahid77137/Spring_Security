package com.masai.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Integer productId;
	@NotNull(message = "productname cannot be null")
	private String productName;
	@NotNull(message = "price cannot be null")
	@Positive(message = "price should in positive")
	private Double price;
	@NotEmpty(message = "description is mandatory to fill")
	private String description;
	@AssertTrue
	private boolean isAvailable;

	
//	product->cart->order
	
	@ManyToOne
	@JoinColumn(name = "cartId")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Order order;
	
}
