package dev.com.app;

import dev.com.controllers.EmployeeControllers;
import dev.com.controllers.ExpenseControllers;
import dev.com.controllers.LoginControllers;
import io.javalin.Javalin;

public class App {
	public static void main(String[] args) {

		Javalin app = Javalin.create(config -> { 

			config.enableCorsForAllOrigins();
			//config.addStaticFiles("/frontend");

		}).start(7000);
		
		
		// for customer
		// read operation
		// app.get(path, handler)
		app.get("/employees", EmployeeControllers.getAllEmployees);
		app.get("/employees/:ID", EmployeeControllers.getEmployeeById);

		// create operation
		app.post("/employees", EmployeeControllers.createEmployee);

		// update operation
		app.put("/employees", EmployeeControllers.updateEmployee);

		// delete operation
		app.delete("/employees/:ID", EmployeeControllers.deleteEmployeeByID);
		
		//for login
		app.post("/login", LoginControllers.createSession);
		
		app.get("/userinfo", LoginControllers.getUserInfo);

		// for expense
		// read operation
		app.get("/expenses", ExpenseControllers.getAllExpenses);
		app.get("/employees/:ID/expenses", ExpenseControllers.getExpenseByEmployee);
		app.get("/employees/:ID/expenses/:expID", ExpenseControllers.getExpenseByID);

		// create operation
		app.post("/employees/:ID/expenses", ExpenseControllers.createExpense);

		// update operation
		app.put("/employees/:ID/expenses/:expID", ExpenseControllers.updateExpense);

		// delete operation
		app.delete("/employees/:ID/expenses/:expID", ExpenseControllers.deleteExpenseByID);

	}

}
