package dev.com.dao;

import java.util.List;

import dev.com.entities.Expense;


public interface ExpenseDAO {
	
	//Create
	Expense createExpense(Expense expense);
	
	//Read
	Expense getExpenseByID(int expID);
	List<Expense> getExpenseByDate(String date);
	List<Expense> getExpenseByType(String type);
	List<Expense> getExpenseByAmount(int amount);
	List<Expense> getExpenseByStatus(String status);
	List<Expense> getExpenseByEmployee(int emplID);
	List<Expense> getAllExpenses();
	
	
	//Update
	Expense updateExpense(Expense expense);
	
	//Delete
	boolean deleteExpenseByID(int ID);


}
