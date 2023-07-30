package com.masaischool.revision_batch_hibernate;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	private String email;
	private String name;
	
	@OneToMany(mappedBy = "manager")
	private Set<ManagerProject> managerProject;

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String email, String name, Set<ManagerProject> managerProject) {
		super();
		this.email = email;
		this.name = name;
		this.managerProject = managerProject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ManagerProject> getManagerProject() {
		return managerProject;
	}

	public void setManagerProject(Set<ManagerProject> managerProject) {
		this.managerProject = managerProject;
	}
}
