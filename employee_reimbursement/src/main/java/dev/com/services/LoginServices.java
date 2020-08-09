package dev.com.services;

import dev.com.dao.EmployeeDAO;
import dev.com.dao.EmployeeDAOImpl;
import dev.com.dto.LoginDTO;
import dev.com.entities.Employee;
import java.util.List;

public class LoginServices {
	
	public static EmployeeDAO edao = EmployeeDAOImpl.getEmployeeDAOImpl();

	public Employee loginUser(LoginDTO dto) {
		
		List<Employee> userList = edao.getAllEmployee();
		
		for(Employee s : userList) {
			
			if(s.getEmail().equals(dto.getEmail()) && s.getPassword().equals(dto.getPassword())) {
				return s;
			}
			
		}
		
		return null;
}
}
