package com.masaischool.revision_batch_hibernate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/**
 * Hello world!
 *
 */
public class App {
	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("revision_batch_hibernate");
	}
	
	static void addEmployeeAccount() {
		EntityManager em = null;
		EntityTransaction et = null;
		
		//Create object of Employee
		Employee employee = new Employee();
		employee.setFirstName("ABC");
		employee.setLastName("PQR");
		employee.setEmail("abc@gmail.com");

		//Create some object of Accounts
		Account accOne = new Account();
		accOne.setAccountNumber("A001");
		accOne.setEmployee(employee);
		
		Account accTwo = new Account();
		accTwo.setAccountNumber("A002");
		accTwo.setEmployee(employee);
		
		//Create set of Account
		Set<Account> accounts = new HashSet<>();
		accounts.add(accOne);
		accounts.add(accTwo);
		//setting all accounts for the employee
		employee.setAccounts(accounts);
		
		try {
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(employee);
			et.commit();
		}catch(Exception ex) {
			et.rollback();
			System.out.println(ex.getMessage());
		}finally {
			em.close();
		}
	}
	
	static void printEmployeeDetails() {
		try(EntityManager em = emf.createEntityManager();){
			String selectQuery = "FROM Employee e";
			Query query = em.createQuery(selectQuery);
			List<Employee> list = (List<Employee>)query.getResultList();
			for(Employee emp : list) {
				System.out.println(emp.getFirstName() + ":" + emp.getLastName() + ":" + emp.getEmail());
				
				Set<Account> accountSet = emp.getAccounts();
				for(Account account: accountSet) {
					System.out.println("\tAccount Number: " + account.getAccountNumber());
				}
			}	
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void printAccountDetails() {
		try(EntityManager em = emf.createEntityManager();){
			String selectQuery = "FROM Account a";
			Query query = em.createQuery(selectQuery);
			List<Account> list = query.getResultList();
			
			for(Account account: list) {
				System.out.println(account.getAccountNumber());
				Employee employee = account.getEmployee();
				System.out.println(employee.getFirstName() + ":" + employee.getLastName() + ":" + employee.getEmail());
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void saveStudent() {
		//Create an object of Student
		Student st = new Student();
		st.setName("ABC");
		st.setEmail("abc@ms.com");
		
		//Create Two Courses
		Course courseOne = new Course();
		courseOne.setCourseName("Java");
		
		Course courseTwo = new Course();
		courseTwo.setCourseName("MEARN");
		
		Set<Course> courseSet = new HashSet<>();
		courseSet.add(courseOne);
		courseSet.add(courseTwo);
		
		//set the courses for the student
		st.setCourses(courseSet);
		
		Set<Student> studentSet = new HashSet<>();
		studentSet.add(st);
		//set the students for the courses
		courseOne.setStudents(studentSet);
		courseTwo.setStudents(studentSet);
		
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			em = emf.createEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(st);
			et.commit();
		}catch(Exception ex) {
			et.rollback();
			System.out.println(ex.getMessage());
		}finally {
			em.close();
		}	
	}
	
	static void printStudentDetails() {
		try(EntityManager em = emf.createEntityManager();){
			String selectQuery = "FROM Student s";
			Query query = em.createQuery(selectQuery);
			List<Student> list = query.getResultList();
			for(Student s: list) {
				System.out.println(s.getName() + ":" + s.getEmail());
				Set<Course> set = s.getCourses();
				for(Course course: set)
					System.out.println("\t" + course.getCourseName());
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void printCourseDetails() {
		try(EntityManager em = emf.createEntityManager();){
			String selectQuery = "FROM Course s";
			Query query = em.createQuery(selectQuery);
			List<Course> list = query.getResultList();
			for(Course c: list) {
				System.out.println(c.getCourseName());
				Set<Student> set = c.getStudents();
				for(Student st: set)
					System.out.println("\t" + st.getName() + ":" + st.getEmail());
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void saveManagerProject() {
		//Create object of Manager
		Manager managerOne = new Manager("Manager-01", "m01@abc.com", new HashSet<>());
		Manager managerTwo = new Manager("Manager-02", "m02@abc.com", new HashSet<>());
		
		//create object of Project
		Project projectOne = new Project("P01", 180, new HashSet<>());
		Project projectTwo = new Project("P02", 120, new HashSet<>());
		Project projectThree = new Project("P03", 90, new HashSet<>());
		
		//Save objects of Manager and Project by calling the persist() method
		EntityManager em = null;
		EntityTransaction et = null;
		
		try {
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
			em.persist(managerOne);
			em.persist(managerTwo);
			
			em.persist(projectOne);
			em.persist(projectTwo);
			em.persist(projectThree);

			//create objects of ManagerProject
			ManagerProject mpOne = new ManagerProject(managerOne, projectOne, LocalDate.parse("2023-05-23"), LocalDate.parse("2023-06-05"));
			ManagerProject mpTwo = new ManagerProject(managerTwo, projectTwo, LocalDate.parse("2023-05-20"), LocalDate.parse("2023-06-05"));
			ManagerProject mpThree = new ManagerProject(managerOne, projectThree, LocalDate.parse("2023-05-15"), LocalDate.parse("2023-05-31"));
			ManagerProject mpFour = new ManagerProject(managerTwo, projectThree, LocalDate.parse("2023-05-05"), LocalDate.parse("2023-05-20"));
			
			//Set the ManagerProject for Managers
			managerOne.getManagerProject().add(mpOne);
			managerOne.getManagerProject().add(mpThree);
			managerTwo.getManagerProject().add(mpTwo);
			managerTwo.getManagerProject().add(mpFour);
			
			//Set the ManagerProject for Projects
			projectOne.getManagerProject().add(mpOne);
			projectTwo.getManagerProject().add(mpTwo);
			projectThree.getManagerProject().add(mpThree);
			projectThree.getManagerProject().add(mpFour);
			
			em.persist(mpOne);
			em.persist(mpTwo);
			em.persist(mpThree);
			em.persist(mpFour);
			
			et.commit();
		}catch(Exception ex) {
			et.rollback();
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	static void printManagerProject() {
		try(EntityManager em = emf.createEntityManager();){
			String selectQuery = "FROM Manager m";
			Query query = em.createQuery(selectQuery);
			List<Manager> list = query.getResultList();
			for(Manager m: list) {
				System.out.println(m.getId() + ", " + m.getName() + ", " + m.getEmail());
				Set<ManagerProject> set = m.getManagerProject();
				for(ManagerProject mp: set)
					System.out.println(mp.getStartDate() + ", " + mp.getEndDate() + ", " + mp.getProject().getName() + 
							", " + mp.getProject().getDuration() );
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	static void printProjectManager() {
		try(EntityManager em = emf.createEntityManager();){
			String selectQuery = "FROM Project m";
			Query query = em.createQuery(selectQuery);
			List<Project> list = query.getResultList();
			for(Project p: list) {
				System.out.println(p.getId() + ", " + p.getName() + ", " + p.getDuration());
				Set<ManagerProject> set = p.getManagerProject();
				for(ManagerProject mp: set)
					System.out.println(mp.getStartDate() + ", " + mp.getEndDate() + ", " + mp.getManager().getEmail() + 
							", " + mp.getManager().getName());
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
    public static void main( String[] args ){
//    	addEmployeeAccount();
//    	printEmployeeDetails();
//    	printAccountDetails();
//    	
//    	saveStudent();
//    	printStudentDetails();
//    	System.out.println("\n");
//    	printCourseDetails();
    	
//    	saveManagerProject();
//    	printManagerProject();
//    	printProjectManager();
    }
}
