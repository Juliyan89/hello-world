package testsDAO;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import dev.com.dao.ExpenseDAO;
import dev.com.dao.ExpenseDAOImpl;
import dev.com.entities.Expense;
import dev.com.exceptions.NegativeAmount;

@TestMethodOrder(OrderAnnotation.class)
class TestsExpenseDAO {

	public static ExpenseDAO expdao = ExpenseDAOImpl.getExpenseDAOImpl();

	@Test
	@Order(1)
	void createExpense1() throws NegativeAmount {
		Expense food = new Expense(0, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		expdao.createExpense(food);
		Assertions.assertNotEquals(0, food.getExpID());
	}

	@Test
	@Order(2)
	void createExpense2() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		expdao.createExpense(fuel);
		Assertions.assertNotEquals(0, fuel.getExpID());
	}

	@Test
	@Order(10)
	void getExpenseByID() {
		Expense expense = expdao.getExpenseByID(1);
		Assertions.assertEquals(1, expense.getExpID());
	}

	@Test
	@Order(11)
	void getExpenseByDate() {
		List<Expense> expenses = expdao.getExpenseByDate("08/02/2020");
		Assertions.assertEquals("08/02/2020", expenses.get(0).getExpDate());
	}

	@Test
	@Order(12)
	void getExpenseByType() {
		List<Expense> expenses = expdao.getExpenseByType("Fuel");
		Assertions.assertEquals("Fuel", expenses.get(0).getExpType());
	}

	@Test
	@Order(13)
	void getExpenseByAmount() {
		List<Expense> expenses = expdao.getExpenseByAmount(30);
		Assertions.assertEquals(30, expenses.get(0).getAmount());
	}

	@Test
	@Order(14)
	void getExpenseByStatus() {
		List<Expense> expenses = expdao.getExpenseByStatus("submit");
		Assertions.assertEquals("submit", expenses.get(0).getStatus());
	}

	@Test
	@Order(15)
	void getAllExpenses() {
		List<Expense> expenses = expdao.getAllExpenses();
		Assertions.assertNotEquals(0, expenses.size());
	}

	@Test
	@Order(20)
	void updateExpense1() {
		Expense expense = expdao.getExpenseByID(1);
		expense.setDescription("Updated Description");
		expdao.updateExpense(expense);
		Assertions.assertEquals("Updated Description", expense.getDescription());

	}

	@Test
	@Order(21)
	void updateEmployee2() throws NegativeAmount {
		Expense expense = expdao.getExpenseByID(1);
		expense.setAmount(150);
		expdao.updateExpense(expense);
		Assertions.assertEquals(150, expense.getAmount());

	}

	@Test
	@Order(30)
	void deleteById() {
		boolean result = expdao.deleteExpenseByID(1);
		Assertions.assertEquals(true, result);

	}

}