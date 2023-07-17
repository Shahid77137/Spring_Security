package com.truck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truck.Exception.TruckException;
import com.truck.model.Truck;
import com.truck.service.TruckService;

@RestController
@RequestMapping("/trucks")
public class TruckController {

	@Autowired
	private TruckService ts;
	
	 @GetMapping
	    @PreAuthorize("hasAuthority('MANAGER')")
	    public ResponseEntity<List<Truck>> getAllTrucks() throws TruckException {
	        List<Truck> trucks = ts.getAllTrucks();
	        return new ResponseEntity<>(trucks,HttpStatus.OK);
	    }
	 
	 @GetMapping("/trucks/{id}")
	    @PreAuthorize("hasAuthority('MANAGER') or (hasAuthority('DRIVER') and #id == authentication.details)")
	    public ResponseEntity<Truck> getTruckById(@PathVariable("id") Long id) throws TruckException {
	        Truck truck = ts.getTruckById(id);
	        return new ResponseEntity<>(truck,HttpStatus.OK);
	    }
	 
	 @PostMapping("/trucks")
	    @PreAuthorize("hasAuthority('MANAGER')")
	    public ResponseEntity<Truck> createTruck(@RequestBody Truck truck) throws TruckException {
	        Truck savedTruck = ts.createTruck(truck);
	        return new ResponseEntity<>(savedTruck,HttpStatus.CREATED);
	    }
	 
	  @PutMapping("/trucks/{id}")
	    @PreAuthorize("hasAuthority('MANAGER') or (hasAuthority('DRIVER') and #truck.driver.id == authentication.details)")
	    public ResponseEntity<Truck> updateTruck(@PathVariable("id") Long id, @RequestBody Truck truck) throws TruckException {
	        Truck updatedTruck = ts.updateTruck(id, truck);
	        return new ResponseEntity<>(updatedTruck,HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    @PreAuthorize("hasAuthority('MANAGER') or (hasAuthority('DRIVER') and #id == authentication.details)")
	    public ResponseEntity<Truck> removeTruck(@PathVariable("id") Long id) throws TruckException {
	       
	        return new ResponseEntity<>(ts.removeTruck(id),HttpStatus.OK);
	    }
	 
	
}
