package com.truck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truck.Exception.TruckException;
import com.truck.Repo.TruckRepo;
import com.truck.model.Truck;

@Service
public class TruckService {

	
	@Autowired
	private TruckRepo tr;
	
	
	public List<Truck> getAllTrucks()throws TruckException{
	List<Truck> list  = tr.findAll();
	if(list.isEmpty()) {
		throw new TruckException("No trucks found");
		
	}
	
	return list;
	}
	
	public Truck getTruckById(Long id) throws TruckException{
		Truck t = tr.findById(id).orElseThrow(()->new TruckException("No any truck is present by this id"));
		
		return t;
	}
	
	public Truck createTruck(Truck truck) throws TruckException{
		if(truck==null) {
			throw new TruckException("Truck filling is mandatory");
			
		}
		
		return tr.save(truck);
	}
	
	public Truck updateTruck(Long id, Truck truck) throws TruckException{
		Truck ex = tr.findById(id).orElseThrow(()->new TruckException("No any truck is present by this id"));
		
		ex.setDriver(truck.getDriver());
		ex.setModel(truck.getModel());
		ex.setLincesePlate(truck.getLincesePlate());
		ex.setId(truck.getId());
		
		tr.save(ex);
		return ex;
	}
	
	public Truck removeTruck(Long id) throws TruckException{
		
		Truck t = tr.findById(id).orElseThrow(()->new TruckException("No truck found with this id"));
		tr.delete(t);
		return t;
	}
	
	
}
