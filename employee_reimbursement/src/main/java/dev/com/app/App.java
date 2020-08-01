package dev.com.app;


import dev.com.controllers.EmployeeControllers;
import io.javalin.Javalin;

public class App {
public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(7000);

		//for customer
		//read operation
		//app.get(path, handler)
		app.get("/employees", EmployeeControllers.getAllEmployees);
		app.get("/employees/:ID", EmployeeControllers.getEmployeeById);
			
		// create operation
		app.post("/employees", EmployeeControllers.createEmployee);
			
		//update operation
		app.put("/employees", EmployeeControllers.updateEmployee);
				
		//delete operation
		app.delete("/employees/:ID", EmployeeControllers.deleteEmployeeByID);
	
			
	}

}
