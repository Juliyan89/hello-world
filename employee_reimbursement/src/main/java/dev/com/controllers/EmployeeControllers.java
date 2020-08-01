package dev.com.controllers;

import java.util.List;
import com.google.gson.Gson;
import dev.com.entities.Employee;
import dev.com.services.EmployeeServices;
import dev.com.services.EmployeeServicesImpl;
import io.javalin.http.Handler;

public class EmployeeControllers {

	public static EmployeeServices eserv = new EmployeeServicesImpl();
	private static Gson gson = new Gson();
	
	
	public static Handler createEmployee = (ctx) -> {
		String employeeJSON = ctx.body();
		Employee employee = gson.fromJson(employeeJSON, Employee.class);

		List<Employee> employees = eserv.getAllEmployee();
		for (Employee empl : employees) {
			if ((employee.getEmail()).equals(empl.getEmail())) {
				ctx.result("There is already Employee with this email !");
				ctx.status(400);
				break;
			}
		}
		if (ctx.status() != 400) {
			eserv.createEmployee(employee);
			ctx.result(gson.toJson(employee));
			ctx.status(201);
		}
	};
		
	
	
	public static Handler getEmployeeById = (ctx) -> {
		String id = ctx.pathParam("ID");
		Employee employee = eserv.getEmployeeByID(Integer.parseInt(id));

		if (employee == null) {
			ctx.status(404);
			ctx.result("There is no Employee with this ID");
		} else {
			String json = gson.toJson(employee);
			ctx.result(json);
		}
	};
	
	public static Handler getAllEmployees = (ctx) -> {
		List<Employee> employees = eserv.getAllEmployee();
		String json = gson.toJson(employees);
		// String userQ = ctx.queryParam("email");
		if(ctx.queryString() != null) {
			
		if (ctx.queryString().contains("email")) {
			String userQ = ctx.queryParam("email");
			List<Employee> employeesByEmail = eserv.getEmployeeByEmail(userQ);
			ctx.result(gson.toJson(employeesByEmail));

		} else if (ctx.queryString().contains("name")) {
			String userQ = ctx.queryParam("name");
			List<Employee> employeesByName = eserv.getEmployeeByName(userQ);
			ctx.result(gson.toJson(employeesByName));

		} else if (ctx.queryString().contains("isManager")) {
			String userQ = ctx.queryParam("isManager");

			if (userQ.equals("true")) {
				List<Employee> managers = eserv.getAllManager();
				ctx.result(gson.toJson(managers));
			}

			else {
				List<Employee> NotManagers = eserv.getAllNotManager();
				ctx.result(gson.toJson(NotManagers));
			}
		}
		}
		else {
			ctx.result(json);
		}
	};
	

	


	public static Handler updateEmployee = (ctx)->{
		String employeeJSON = ctx.body();
		Employee employee = gson.fromJson(employeeJSON, Employee.class);
		
		if(eserv.getEmployeeByID(employee.getID())==null) {
			ctx.status(404);
			ctx.result("There is no Employee with this ID");
		} else {
			eserv.updateEmployee(employee);
			ctx.result(gson.toJson(employee));}
	};
	
	
	public static Handler deleteEmployeeByID = (ctx) ->{
		String id = ctx.pathParam("ID");
		Employee employee = eserv.getEmployeeByID(Integer.parseInt(id));
		
		if(employee == null) {
			ctx.status(404);
			ctx.result("There is no Employee with this ID");
		} else {
			eserv.deleteEmployeeByID(employee.getID());
			ctx.result(gson.toJson(employee));} 
		
	};
		

	
}
