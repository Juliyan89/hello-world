package dev.com.services;

import java.util.List;

import dev.com.dao.EmployeeDAO;
import dev.com.dao.EmployeeDAOImpl;
import dev.com.dto.LoginDTO;
import dev.com.entities.Employee;

public class EmployeeServicesImpl implements EmployeeServices{
	

	//private static EmployeeDAO edao = EmployeeDAOImpl.getEmployeeDAOImpl();
	
	private EmployeeDAO edao;
	
	
	
	public EmployeeServicesImpl() {
		super();
		this.edao = EmployeeDAOImpl.getEmployeeDAOImpl();
	}
	
	public EmployeeServicesImpl(EmployeeDAO edao) {
		this.edao = EmployeeDAOImpl.getEmployeeDAOImpl();	
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return edao.createEmployee(employee);
	}

	@Override
	public Employee getEmployeeByID(int ID) {
		return edao.getEmployeeByID(ID);
	}

	@Override
	public List<Employee> getEmployeeByEmail(String email) {
		return edao.getEmployeeByEmail(email);
	}
	
	@Override
	public List<Employee> getEmployeeByName(String name) {
		return edao.getEmployeeByName(name);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return edao.getAllEmployee();
	}

	@Override
	public List<Employee> getAllManager() {
		return edao.getAllManager();
	}

	@Override
	public List<Employee> getAllNotManager() {
		return edao.getAllNotManager();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return edao.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployeeByID(int ID) {
		return edao.deleteEmployeeByID(ID);
	}

	@Override
	public boolean deleteEmployeeByEmail(String email) {
		return edao.deleteEmployeeByEmail(email);
	}
	



}
