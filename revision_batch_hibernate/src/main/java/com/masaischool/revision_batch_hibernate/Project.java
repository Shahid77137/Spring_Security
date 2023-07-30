package com.masaischool.revision_batch_hibernate;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	private String name;
	private int duration;	//duration in days
	
	@OneToMany(mappedBy = "project")
	private Set<ManagerProject> managerProject;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(String name, int duration, Set<ManagerProject> managerProject) {
		super();
		this.name = name;
		this.duration = duration;
		this.managerProject = managerProject;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Set<ManagerProject> getManagerProject() {
		return managerProject;
	}

	public void setManagerProject(Set<ManagerProject> managerProject) {
		this.managerProject = managerProject;
	}
}
