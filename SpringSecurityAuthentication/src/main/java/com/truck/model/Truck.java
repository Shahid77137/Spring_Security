package com.truck.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Truck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String model;
	
	private String lincesePlate;
	
	@ManyToOne
	@JoinColumn(name ="driver_id")
	private User driver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLincesePlate() {
		return lincesePlate;
	}

	public void setLincesePlate(String lincesePlate) {
		this.lincesePlate = lincesePlate;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public Truck(String model, String lincesePlate, User driver) {
		super();
		this.model = model;
		this.lincesePlate = lincesePlate;
		this.driver = driver;
	}

	@Override
	public int hashCode() {
		return Objects.hash(driver, id, lincesePlate, model);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Truck other = (Truck) obj;
		return Objects.equals(driver, other.driver) && Objects.equals(id, other.id)
				&& Objects.equals(lincesePlate, other.lincesePlate) && Objects.equals(model, other.model);
	}
	
	
	
	
}
