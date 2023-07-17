package com.truck.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truck.model.Truck;

public interface TruckRepo extends JpaRepository<Truck, Long> {

	
	
}
