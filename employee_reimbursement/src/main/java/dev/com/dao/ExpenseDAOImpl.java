package dev.com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dev.com.entities.Employee;
import dev.com.entities.Expense;
import dev.com.utility.UtilityHibernate;

public class ExpenseDAOImpl implements ExpenseDAO{
	
	private static ExpenseDAO expdao = null;

	private ExpenseDAOImpl() {

	}

	public static ExpenseDAO getExpenseDAOImpl() {

		if (expdao == null) {
			expdao = new ExpenseDAOImpl();
		}

		return expdao;
	}
	
	// session factory creates sessions
	private static SessionFactory sf = UtilityHibernate.getSessionFactory();

	
	@Override
	public Expense createExpense(Expense expense) {
		try(Session sess = sf.openSession()){
		sess.beginTransaction();
		sess.save(expense);
		sess.getTransaction();
		sess.close();			
		return expense;}
	}

	@Override
	public Expense getExpenseByID(int expID) {
		try(Session sess = sf.openSession()){
		Expense expense = sess.get(Expense.class, expID);
		sess.close();
		return expense;
		}
	}

	@Override
	public List<Expense> getExpenseByDate(String expDate) {
		try(Session sess = sf.openSession()){
		Criteria crit = sess.createCriteria(Expense.class);
		crit.add(Restrictions.like("expDate", expDate));
		List<Expense> expenses = crit.list();
		return expenses;
		}
	}

	@Override
	public List<Expense> getExpenseByType(String expType) {
		try(Session sess = sf.openSession()){
		Criteria crit = sess.createCriteria(Expense.class);
		crit.add(Restrictions.like("expType", expType));
		List<Expense> expenses = crit.list();
		return expenses;
		}
	}

	@Override
	public List<Expense> getExpenseByAmount(int amount) {
		try(Session sess = sf.openSession()){
		Criteria crit = sess.createCriteria(Expense.class);
		crit.add(Restrictions.like("amount", amount));
		List<Expense> expenses = crit.list();
		return expenses;
		}
	}

	@Override
	public List<Expense> getExpenseByStatus(String status) {
		try(Session sess = sf.openSession()){
		Criteria crit = sess.createCriteria(Expense.class);
		crit.add(Restrictions.like("status", status));
		List<Expense> expenses = crit.list();
		return expenses;
		}
	}

	@Override
	public List<Expense> getAllExpenses() {
		try(Session sess = sf.openSession()){
		Criteria crit = sess.createCriteria(Expense.class);
		List<Expense> expenses = crit.list();
		return expenses;
		}
	}

	@Override
	public Expense updateExpense(Expense expense) {
		try(Session sess = sf.openSession()){ 
		sess.beginTransaction(); 
		sess.update(expense);
		sess.getTransaction().commit(); 
		sess.close(); 
		return expense;
		}
	}

	@Override
	public boolean deleteExpenseByID(int ID) {
		Transaction transaction = null;
		try(Session sess = sf.openSession()){
		Expense expense = sess.get(Expense.class, ID);
		transaction = sess.beginTransaction(); 
		sess.delete(expense);
		sess.getTransaction().commit(); 
		sess.close(); 

        if (transaction != null) {
            return true;
        }
        return false;
		}
	}

	@Override
	public List<Expense> getExpenseByEmployee(int emplID) {
		try(Session sess = sf.openSession()){
		Criteria crit = sess.createCriteria(Expense.class);
		crit.add(Restrictions.like("emplID", emplID));
		List<Expense> expenses = crit.list();
		return expenses;
		}
	}


	
	
}
