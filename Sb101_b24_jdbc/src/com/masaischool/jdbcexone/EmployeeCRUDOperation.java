package com.masaischool.jdbcexone;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeCRUDOperation {
	
	static Connection getConnectionTodatabase() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResourceBundle rb = ResourceBundle.getBundle("dbdetails");
		return DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));
	}
	
	
	static void closeConnection(Connection conn) throws SQLException {
		if(conn != null)
			conn.close();
	}
	
	
	static void addEmployee(Scanner sc) {
	
		Connection conn = null;
		try {
			conn = getConnectionTodatabase();
			
			String query = "INSERT INTO employee (eid, name, salary, joining_date) VALUES (?, ?, ?, ?)";
			

			PreparedStatement ps = conn.prepareStatement(query);
			
			//System.out.println(ps);
			
			System.out.print("Enter employee id ");
			String eid = sc.next();
			
			System.out.print("Enter employee name ");
			String name = sc.next();
			
			System.out.print("Enter salary ");
			double salary = sc.nextDouble();
			
			System.out.print("Enter joining date ");
			LocalDate joiningDate = LocalDate.parse(sc.next());
			
			ps.setString(1, eid);
			ps.setString(2, name);
			ps.setDouble(3, salary);
			ps.setDate(4, Date.valueOf(joiningDate));
			
			if(ps.executeUpdate() > 0) {
				System.out.println("Employee added successfully");
			}else {
				System.out.println("Unable to add Employee");
			}
		}catch(ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}finally {
			if(conn != null) {
				try {
					closeConnection(conn);
				}catch(SQLException ex) {
					
				}
			}
		}
	}
	
	static void updateEmployee(Scanner sc) {
		Connection conn = null;
		try {
			conn = getConnectionTodatabase();
			
			String query = "UPDATE employee SET name = ?, salary = ? , joining_date = ? WHERE eid = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			//step-04 Take data input
			System.out.print("Enter employee id ");
			String eid = sc.next();
			
			System.out.print("Enter employee name ");
			String name = sc.next();
			
			System.out.print("Enter salary ");
			double salary = sc.nextDouble();
			
			System.out.print("Enter joining date ");
			LocalDate joiningDate = LocalDate.parse(sc.next());
			
			//step-04: data stuffing
			ps.setString(1, name);
			ps.setDouble(2, salary);
			ps.setDate(3, Date.valueOf(joiningDate));
			ps.setString(4, eid);
			
			//System.out.println(ps);
			if(ps.executeUpdate() > 0) {
				System.out.println("Employee updated successfully");
			}else {
				System.out.println("Unable to update Employee");
			}
		}catch(ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}finally {
			if(conn != null) {
				try {
					closeConnection(conn);
				}catch(SQLException ex) {
					
				}
			}
		}
	}
	
	
	static void deleteEmployee(Scanner sc) {
		Connection conn = null;
		try {
			conn = getConnectionTodatabase();
			
			//step-02 Frame query
			String query = "DELETE FROM employee WHERE eid = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			System.out.print("Enter employee id ");
			String eid = sc.next();
			
			ps.setString(1, eid);
			
			//System.out.println(ps);
			if(ps.executeUpdate() > 0) {
				System.out.println("Employee deleted successfully");
			}else {
				System.out.println("Unable to delete Employee");
			}
		}catch(ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}finally {
			if(conn != null) {
				try {
					closeConnection(conn);
				}catch(SQLException ex) {
					
				}
			}
		}
	}
	
	static void viewEmployee() {
		Connection conn = null;
		try {
			conn = getConnectionTodatabase();
			
			String query = "SELECT eid, name, salary, joining_date FROM employee";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst() && rs.getRow() == 0) {
				System.out.println("No Employee Found");
			}else{
				while(rs.next()) {
					System.out.print("Employee Id: " + rs.getString(1));
					System.out.print(" Name: " + rs.getString(2));
					System.out.print(" Salary: " + rs.getDouble(3));
					System.out.println(" Joining Date: " + rs.getDate(4));
				}
			}
			
		}catch(ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}finally {
			if(conn != null) {
				try {
					closeConnection(conn);
				}catch(SQLException ex) {
				
				}
			}
		}
	}
	
	static void searchEmployeeByJoiningDateRange(Scanner sc) {
		Connection conn = null;
		try {
			conn = getConnectionTodatabase();
			
			String query = "SELECT eid, name, salary, joining_date FROM employee WHERE joining_date BETWEEN ? AND ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			System.out.print("Please enter start date ");
			Date startDate = Date.valueOf(LocalDate.parse(sc.next()));
			System.out.print("Please enter end date ");
			Date endDate = Date.valueOf(LocalDate.parse(sc.next()));
			
			ps.setDate(1, startDate);
			ps.setDate(2, endDate);
			
			ResultSet rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst() && rs.getRow() == 0) {
				System.out.println("No Employee Found");
			}else{
				while(rs.next()) {
					System.out.print("Employee Id: " + rs.getString(1));
					System.out.print(" Name: " + rs.getString(2));
					System.out.print(" Salary: " + rs.getDouble(3));
					System.out.println(" Joining Date: " + rs.getDate(4));
				}
			}
			
		}catch(ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}finally {
			if(conn != null) {
				try {
					closeConnection(conn);
				}catch(SQLException ex) {
					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("0. Exit");
			System.out.println("1. Add Employee");
			System.out.println("2. View Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Search Employee By Joining Date Range");
			System.out.print("Enter Selection ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					addEmployee(sc);
					break;
				case 2:
					viewEmployee();
					break;
				case 3:
					updateEmployee(sc);
					break;
				case 4:
					deleteEmployee(sc);
					break;
				case 5:
					searchEmployeeByJoiningDateRange(sc);
					break;
				case 0:
					System.out.println("Bye Bye");
					break;
				default:
					System.out.println("Invalid Selection please try again later");
					break;
			}
			
		}while(choice != 0);
		sc.close();
	}
}
