package com.masaischool.revision_batch_hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {	//this is owning side
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String accountNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//use @JoinColumn at owning side and it will contains name of the foreign key column
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Account() {
		super();
	}

	public Account(String accountNumber, Employee employee) {
		super();
		this.accountNumber = accountNumber;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
