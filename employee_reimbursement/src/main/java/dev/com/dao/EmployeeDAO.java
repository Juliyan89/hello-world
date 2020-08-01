package dev.com.dao;

import java.util.List;
import dev.com.entities.Employee;


public interface EmployeeDAO {
	
	//Create
	Employee createEmployee(Employee employee);
	
	//Read
	Employee getEmployeeByID(int ID);
	List<Employee> getEmployeeByEmail(String email);
	List<Employee> getEmployeeByName(String name);
	List<Employee> getAllEmployee();
	List<Employee> getAllManager();
	List<Employee> getAllNotManager();
	
	
	//Update
	Employee updateEmployee(Employee employee);
	
	//Delete
	boolean deleteEmployeeByID(int ID);
	boolean deleteEmployeeByEmail(String email);
	

}
