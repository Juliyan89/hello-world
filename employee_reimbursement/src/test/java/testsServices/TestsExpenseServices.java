package testsServices;



import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import dev.com.dao.ExpenseDAO;
import dev.com.entities.Expense;
import dev.com.exceptions.NegativeAmount;
import dev.com.services.ExpenseServicesImpl;

@TestMethodOrder(OrderAnnotation.class)
class TestsExpenseServices {

	ExpenseDAO expdao = Mockito.mock(ExpenseDAO.class);
	ExpenseServicesImpl expserv = new ExpenseServicesImpl(expdao);

	@Test
	@Order(1)
	void createExpense1() throws NegativeAmount {
		Expense food = new Expense(0, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		Mockito.when(expdao.createExpense(food)).thenReturn(food);
		expserv.createExpense(food);
		Assertions.assertEquals("Food Allowance", food.getExpType());
	}

	@Test
	@Order(2)
	void createExpense2() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Mockito.when(expdao.createExpense(fuel)).thenReturn(fuel);
		expserv.createExpense(fuel);
		Assertions.assertEquals("Fuel", fuel.getExpType());
	}

	@Test
	@Order(10)
	void getExpenseByID() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Expense food = new Expense(1, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(fuel);
		expenses.add(food);
		Mockito.when(expdao.getExpenseByID(1)).thenReturn(expenses.get(0));
		Expense exp = expserv.getExpenseByID(1);
		Assertions.assertEquals(1, exp.getExpID());
	}

	@Test
	@Order(11)
	void getExpenseByDate() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Expense food = new Expense(1, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(fuel);
		expenses.add(food);
		Mockito.when(expdao.getExpenseByDate("08/02/2020")).thenReturn(expenses);
		List<Expense> exp = expserv.getExpenseByType("Fuel");
		Assertions.assertEquals("08/02/2020", exp.get(0).getExpDate());
	}

	@Test
	@Order(12)
	void getExpenseByType() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Expense food = new Expense(1, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(fuel);
		expenses.add(food);
		Mockito.when(expdao.getExpenseByType("Fuel")).thenReturn(expenses);
		List<Expense> exp = expserv.getExpenseByType("Fuel");
		Assertions.assertEquals("Fuel", exp.get(0).getExpType());
	}

	@Test
	@Order(13)
	void getExpenseByAmount() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Expense food = new Expense(1, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(fuel);
		expenses.add(food);
		Mockito.when(expdao.getExpenseByAmount(30)).thenReturn(expenses);
		List<Expense> exp = expserv.getExpenseByAmount(30);
		Assertions.assertEquals(30, exp.get(0).getAmount());
	}

	@Test
	@Order(14)
	void getExpenseByStatus() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Expense food = new Expense(1, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(fuel);
		expenses.add(food);
		Mockito.when(expdao.getExpenseByStatus("submit")).thenReturn(expenses);
		List<Expense> exp = expserv.getExpenseByStatus("submit");
		Assertions.assertEquals("submit", exp.get(0).getStatus());
	}

	@Test
	@Order(15)
	void getAllExpenses() throws NegativeAmount {
		Expense fuel = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Expense food = new Expense(1, "08/03/2020", "Food Allowance", "Daily Food Allowance", 30, 1, "submit");
		List<Expense> expenses = new ArrayList<Expense>();
		expenses.add(fuel);
		expenses.add(food);
		Mockito.when(expdao.getAllExpenses()).thenReturn(expenses);
		List<Expense> exp = expserv.getAllExpenses();
		Assertions.assertNotEquals(0, exp.size());
	}

	@Test
	@Order(20)
	void updateExpense1() throws NegativeAmount {
		Expense expense = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Mockito.when(expdao.updateExpense(expense)).thenReturn(expense);
		Expense exp = expserv.getExpenseByID(1);
		exp.setDescription("Updated Description");
		expserv.updateExpense(exp);
		Assertions.assertEquals("Updated Description", exp.getDescription());

	}

	@Test
	@Order(21)
	void updateEmployee2() throws NegativeAmount {
		Expense expense = new Expense(0, "08/02/2020", "Fuel", "200 miles", 60, 1, "submit");
		Mockito.when(expdao.updateExpense(expense)).thenReturn(expense);
		Expense exp = expserv.getExpenseByID(1);
		exp.setAmount(150);
		expserv.updateExpense(exp);
		Assertions.assertEquals(150, exp.getAmount());
	}

	@Test
	@Order(30)
	void deleteById() {
		Mockito.when(expdao.deleteExpenseByID(1)).thenReturn(true);
		boolean result = expserv.deleteExpenseByID(1);
		Assertions.assertEquals(true, result);

	}

}