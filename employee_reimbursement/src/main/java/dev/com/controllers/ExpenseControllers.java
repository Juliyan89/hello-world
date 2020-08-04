package dev.com.controllers;

import java.util.List;
import com.google.gson.Gson;

import dev.com.entities.Employee;
import dev.com.entities.Expense;
import dev.com.services.EmployeeServices;
import dev.com.services.EmployeeServicesImpl;
import dev.com.services.ExpenseServices;
import dev.com.services.ExpenseServicesImpl;
import io.javalin.http.Handler;

public class ExpenseControllers {

	public static ExpenseServices expserv = new ExpenseServicesImpl();
	public static EmployeeServices emplserv = new EmployeeServicesImpl();
	
	private static Gson gson = new Gson();

	public static Handler createExpense = (ctx) -> {
		String json = ctx.body();
		Expense expense = gson.fromJson(json, Expense.class);
		expserv.createExpense(expense);
		ctx.result(gson.toJson(expense));
		ctx.status(201);
		
	};
	
	
	public static Handler getExpenseByID = (ctx) -> {
		String expID = ctx.pathParam("expID");
		String emplID = ctx.pathParam("ID");
		
		Expense expenses = expserv.getExpenseByID(Integer.parseInt(expID));
		Employee employee = emplserv.getEmployeeByID(Integer.parseInt(emplID));
		
		if(employee.getID() == expenses.getEmplID()) {
			String json = gson.toJson(expenses);
			ctx.result(json);
		}else { ctx.status(404);
			    ctx.result("There is no expense with this ID for this employee");}
	};
	
	public static Handler getExpenseByEmployee = (ctx) -> {
		String emplID = ctx.pathParam("ID");
		Employee employee = emplserv.getEmployeeByID(Integer.parseInt(emplID));
		List<Expense> expenses = expserv.getExpenseByEmployee(employee.getID());
		if(expenses.size() == 0) {
			ctx.status(404);
		    ctx.result("There is no expenses for this employee");}

		else { String json = gson.toJson(expenses);
		       ctx.result(json);}
		};

	public static Handler getAllExpenses = (ctx) -> {
		List<Expense> expenses = expserv.getAllExpenses();
		String json = gson.toJson(expenses);

		if (ctx.queryString() != null) {

			if (ctx.queryString().contains("expDate")) {
				String userQ = ctx.queryParam("expDate");
				List<Expense> expByDate = expserv.getExpenseByDate(userQ);
				ctx.result(gson.toJson(expByDate));

			} else if (ctx.queryString().contains("expType")) {
				String userQ = ctx.queryParam("expType");
				List<Expense> expByType = expserv.getExpenseByType(userQ);
				ctx.result(gson.toJson(expByType));

			} else if (ctx.queryString().contains("amount")) {
				String userQ = ctx.queryParam("amount");
				List<Expense> expByAmount = expserv.getExpenseByAmount(Integer.parseInt(userQ));
				ctx.result(gson.toJson(expByAmount));
			} else if (ctx.queryString().contains("status")) {
				String userQ = ctx.queryParam("status");
				List<Expense> expByStatus = expserv.getExpenseByStatus(userQ);
				ctx.result(gson.toJson(expByStatus));
			}
			
		} else {
			ctx.result(json);
		}
	};

	public static Handler updateExpense = (ctx) -> {
		String json = ctx.body();
		Expense expense = gson.fromJson(json, Expense.class);

		if (expserv.getExpenseByID(expense.getExpID()) == null){
			ctx.status(404);
			ctx.result("There is no expense with this ID");
		} else {
			expserv.updateExpense(expense);
			ctx.result(gson.toJson(expense));
		}
	};

	public static Handler deleteExpenseByID = (ctx) -> {
		String id = ctx.pathParam("ID");
		Expense expense = expserv.getExpenseByID(Integer.parseInt(id));

		if (expense == null) {
			ctx.status(404);
			ctx.result("There is no Employee with this ID");
		} else {
			expserv.deleteExpenseByID(expense.getEmplID());
			ctx.result(gson.toJson(expense));
		}

	};

}