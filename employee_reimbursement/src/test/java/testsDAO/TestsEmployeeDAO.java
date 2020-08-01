package testsDAO;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import dev.com.dao.EmployeeDAO;
import dev.com.dao.EmployeeDAOImpl;
import dev.com.entities.Employee;

@TestMethodOrder(OrderAnnotation.class)
class TestsEmployeeDAO {
	
	
	public static EmployeeDAO edao = EmployeeDAOImpl.getEmployeeDAOImpl();
	

	@Test
	@Order(1)
	void createEmployee() {
		Employee minion = new Employee(0,"Kevin","kevin@de.me","Kevpass", false);
		edao.createEmployee(minion);
		Assertions.assertNotEquals(0,minion.getID());
	}
	
	@Test
	@Order(2)
	void createManager() {
		Employee gru = new Employee(0,"Gru","gru@de.me","Grupass", true);
		edao.createEmployee(gru);
		Assertions.assertNotEquals(0,gru.getID());
	}
	
	@Test
	@Order(10)
	void getEmployeeByID() {
		Employee employee = edao.getEmployeeByID(2);
		Assertions.assertEquals(2,employee.getID());
	}
	
	@Test
	@Order(11)
	void getEmployeeByEmail() {
		List<Employee> employees = edao.getEmployeeByEmail("gru@de.me");
		Assertions.assertEquals("gru@de.me",employees.get(0).getEmail());
	}
	
	@Test
	@Order(12)
	void getAllEmployee() {
		List<Employee> employees = edao.getAllEmployee();
		Assertions.assertNotEquals(0,employees.size());
	}
	
	@Test
	@Order(13)
	void getAllManager() {
		List<Employee> employees = edao.getAllManager();
		Assertions.assertEquals(true,employees.get(0).isManager());
	}
	
	@Test
	@Order(14)
	void getAllNotManager() {
		List<Employee> employees = edao.getAllNotManager();
		Assertions.assertEquals(false,employees.get(0).isManager());
	}
	
	@Test
	@Order(15)
	void getEmployeeByName() {
		List<Employee> employees = edao.getEmployeeByName("Kevin");
		Assertions.assertEquals("Kevin",employees.get(0).getName());
	}
	
	@Test
	@Order(20)
	void updateEmployee1() {
		Employee employee = edao.getEmployeeByID(1);
		employee.setName("Stuard");
		edao.updateEmployee(employee);
		Assertions.assertEquals("Stuard", employee.getName());
		
	}
	
	@Test
	@Order(20)
	void updateEmployee2() {
		Employee employee = edao.getEmployeeByID(1);
		employee.setEmail("sturad@de.me");
		edao.updateEmployee(employee);
		Assertions.assertEquals("sturad@de.me", employee.getEmail());
		
	}
	
	@Test
	@Order(30)
	void deleteById() {
		boolean result = edao.deleteEmployeeByID(1);
		Assertions.assertEquals(true, result);
		
	}
	
	@Test
	@Order(30)
	void deleteByEmail() {
		boolean result = edao.deleteEmployeeByEmail("gru@de.me");
		Assertions.assertEquals(true, result);
		
	}


}
