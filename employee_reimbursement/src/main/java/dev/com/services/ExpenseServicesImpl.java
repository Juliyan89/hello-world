package dev.com.services;


import java.util.List;

import dev.com.dao.ExpenseDAO;
import dev.com.dao.ExpenseDAOImpl;
import dev.com.entities.Expense;

public class ExpenseServicesImpl implements ExpenseServices {

	private ExpenseDAO expdao;

	public ExpenseServicesImpl() {
		super();
		this.expdao = ExpenseDAOImpl.getExpenseDAOImpl();
	}

	public ExpenseServicesImpl(ExpenseDAO expdao) {
		this.expdao = ExpenseDAOImpl.getExpenseDAOImpl();
	}

	@Override
	public Expense createExpense(Expense expense) {
		return expdao.createExpense(expense);
	}

	@Override
	public Expense getExpenseByID(int expID) {
		return expdao.getExpenseByID(expID);
	}

	@Override
	public List<Expense> getExpenseByDate(String date) {
		return expdao.getAllExpenses();
	}

	@Override
	public List<Expense> getExpenseByType(String type) {
		return expdao.getExpenseByType(type);
	}

	@Override
	public List<Expense> getExpenseByAmount(int amount) {
		return expdao.getExpenseByAmount(amount);
	}

	@Override
	public List<Expense> getExpenseByStatus(String status) {
		return expdao.getExpenseByStatus(status);
	}

	@Override
	public List<Expense> getAllExpenses() {
		return expdao.getAllExpenses();
	}

	@Override
	public Expense updateExpense(Expense expense) {
		return expdao.updateExpense(expense);
	}

	@Override
	public boolean deleteExpenseByID(int ID) {
		return expdao.deleteExpenseByID(ID);
	}

	@Override
	public List<Expense> getExpenseByEmployee(int emplID) {
		return expdao.getExpenseByEmployee(emplID);
	}

}
