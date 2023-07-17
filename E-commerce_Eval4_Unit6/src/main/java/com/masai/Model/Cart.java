package com.masai.Model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartId")
	private Long cartId;
	@NotNull(message = "grandTotal cannot be null")
	@Positive(message = "grandTotal should be in positive")
	private Double grandTotal;
	@NotNull(message = "Total cannot be null")
	@Positive(message = "Total should be in positive")
	private Double total;
	@NotNull(message = "productCount cannot be null")
	@Positive(message = "productCount should be in positive")
	private Integer productCount;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Order> order;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
	private Set<Product> products;

	@Override
	public int hashCode() {
		return Objects.hash(cartId, grandTotal, order, productCount, products, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(grandTotal, other.grandTotal)
				&& Objects.equals(order, other.order) && Objects.equals(productCount, other.productCount)
				&& Objects.equals(products, other.products) && Objects.equals(total, other.total);
	}

	public Long getCartId() {
		return cartId;
	}


	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", grandTotal=" + grandTotal + ", total=" + total + ", productCount="
				+ productCount + ", order=" + order + ", products=" + products + "]";
	} 
	
}
