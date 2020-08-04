package testsServices;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import dev.com.dao.EmployeeDAO;
import dev.com.entities.Employee;
import dev.com.services.EmployeeServicesImpl;
import org.mockito.Mockito;



@TestMethodOrder(OrderAnnotation.class)
class TestsEmployeeServices {
	
	//private static EmployeeServices eServ = new EmployeeServicesImpl();
	EmployeeDAO edao = Mockito.mock(EmployeeDAO.class);
	EmployeeServicesImpl eserv = new EmployeeServicesImpl(edao);
	
	@Test
	@Order(1)
	void createEmployee1() {
		
		Employee minion = new Employee(0,"Kevin","kevin@de.me","Kevpass", false);
		Mockito.when(edao.createEmployee(minion)).thenReturn(minion);
		eserv.createEmployee(minion);
		Assertions.assertEquals("Kevin",minion.getName());
	}

	
	@Test
	@Order(2)
	void createManager() {
		Employee gru = new Employee(0,"Gru","gru@de.me","Grupass", true);
		Mockito.when(edao.createEmployee(gru)).thenReturn(gru);
		eserv.createEmployee(gru);
		Assertions.assertEquals("Gru",gru.getName());
		
	}
	
	@Test
	@Order(10)
	void getEmployeeByID() {
		Employee minion = new Employee(1,"Kevin","kevin@de.me","Kevpass", false);
		Employee gru = new Employee(2,"Gru","gru@de.me","Grupass", true);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(minion);
		employees.add(gru);
		Mockito.when(edao.getEmployeeByID(1)).thenReturn(employees.get(0));
		Employee employee = eserv.getEmployeeByID(1);
		Assertions.assertEquals(1,employee.getID());
	}
	
	@Test
	@Order(11)
	void getEmployeeByEmail() {
		Employee minion = new Employee(1,"Kevin","kevin@de.me","Kevpass", false);
		Employee gru = new Employee(2,"Gru","gru@de.me","Grupass", true);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(minion);
		employees.add(gru);
		Mockito.when(edao.getEmployeeByEmail("kevin@de.me")).thenReturn(employees);
		List<Employee> empls = eserv.getEmployeeByEmail("kevin@de.me");
		Assertions.assertEquals("kevin@de.me",empls.get(0).getEmail());
	}
	
	@Test
	@Order(12)
	void getAllEmployee() {
		Employee minion = new Employee(1,"Kevin","kevin@de.me","Kevpass", false);
		Employee gru = new Employee(2,"Gru","gru@de.me","Grupass", true);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(minion);
		employees.add(gru);
		Mockito.when(edao.getAllEmployee()).thenReturn(employees);
		List<Employee> empls = eserv.getAllEmployee();
		Assertions.assertEquals(2,empls.size());
	}
	
	
	@Test
	@Order(15)
	void getEmployeeByName() {
		Employee minion = new Employee(1,"Kevin","kevin@de.me","Kevpass", false);
		Employee gru = new Employee(2,"Gru","gru@de.me","Grupass", true);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(minion);
		employees.add(gru);
		Mockito.when(edao.getEmployeeByName("Gru")).thenReturn(employees);
		List<Employee> empls = eserv.getEmployeeByName("Gru");
		Assertions.assertEquals("Gru",empls.get(0).getName());
	}
	
	
	@Test
	@Order(20)
	void updateEmployee1() {
		Employee minion = new Employee(1,"Kevin","kevin@de.me","Kevpass", false);
		Mockito.when(edao.updateEmployee(minion)).thenReturn(minion);
		minion.setName("Sturad");
		eserv.updateEmployee(minion);
		Assertions.assertEquals("Sturad",minion.getName());
	}
	
	@Test
	@Order(21)
	void updateEmployee2() {
		Employee minion = new Employee(1,"Kevin","kevin@de.me","Kevpass", false);
		Mockito.when(edao.updateEmployee(minion)).thenReturn(minion);
		minion.setEmail("sturad@de.me");
		eserv.updateEmployee(minion);
		Assertions.assertEquals("sturad@de.me",minion.getEmail());		
	}
	
	@Test
	@Order(30)
	void deleteById() {
		Mockito.when(edao.deleteEmployeeByID(1)).thenReturn(true);
		boolean result = eserv.deleteEmployeeByID(1);
		Assertions.assertEquals(true, result);
		
	}
	


}