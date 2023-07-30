package com.masaischool.revision_batch_hibernate;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class ManagerProject {
	//@Id: To create the primary key
	//@Embedded: used to integrate one object into another object
	//@EmbeddedId: used to integrate an object as primary key
	
	//this class is for Composite primary key
	@Embeddable
	public static class ManagerProjectId implements Serializable{
	    private static final long serialVersionUID = 1L;

		private Long managerId;
		private Long projectId;
		
		public ManagerProjectId() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ManagerProjectId(Long managerId, Long projectId) {
			super();
			this.managerId = managerId;
			this.projectId = projectId;
		}

		public Long getManagerId() {
			return managerId;
		}

		public void setManagerId(Long managerId) {
			this.managerId = managerId;
		}

		public Long getProjectId() {
			return projectId;
		}

		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((managerId == null) ? 0 : managerId.hashCode());
			result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ManagerProjectId other = (ManagerProjectId) obj;
			if (managerId == null) {
				if (other.managerId != null)
					return false;
			} else if (!managerId.equals(other.managerId))
				return false;
			if (projectId == null) {
				if (other.projectId != null)
					return false;
			} else if (!projectId.equals(other.projectId))
				return false;
			return true;
		}
	}//nested class ends here
	
	
	//to denote a composite primary key that is an embeddable class.
	//Make sure to initialize this field here otherwise you will end up with Exception
	@EmbeddedId
	private ManagerProjectId employeeProjectId = new ManagerProjectId();	//this will create two columns (managerId, projectId)
	
	@ManyToOne
	//provides the mapping for an EmbeddedId primary key, an attribute within an EmbeddedId primary key
	@MapsId("managerId")
	private Manager manager;
	
	@ManyToOne
	@MapsId("projectId")
	private Project project;
	
	LocalDate startDate;
	LocalDate endDate;
	
	public ManagerProject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerProject(Manager manager, Project project, LocalDate startDate, LocalDate endDate) {
		super();
		this.manager = manager;
		this.project = project;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
