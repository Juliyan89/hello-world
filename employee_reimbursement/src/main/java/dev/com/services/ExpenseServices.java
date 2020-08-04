package dev.com.services;

import java.util.List;

import dev.com.entities.Expense;
import dev.com.exceptions.NegativeAmount;

public interface ExpenseServices {

	//Create
	Expense createExpense(Expense expense) throws NegativeAmount;
	
	//Read
	Expense getExpenseByID(int expID);
	List<Expense> getExpenseByDate(String date);
	List<Expense> getExpenseByType(String type);
	List<Expense> getExpenseByAmount(int amount);
	List<Expense> getExpenseByStatus(String status);
	List<Expense> getExpenseByEmployee(int emplID);
	List<Expense> getAllExpenses();
	
	
	//Update
	Expense updateExpense(Expense expense) throws NegativeAmount;
	
	//Delete
	boolean deleteExpenseByID(int ID);
}
