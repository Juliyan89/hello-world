package dev.com.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import dev.com.entities.Employee;
import dev.com.utility.UtilityHibernate;

public class EmployeeDAOImpl implements EmployeeDAO {
	
		
		private static EmployeeDAO edao = null;
	
		private EmployeeDAOImpl() {
			
		}
		
		public static EmployeeDAO getEmployeeDAOImpl() {
			
			if(edao == null) {
				edao = new EmployeeDAOImpl();
			}
			
			return edao;
		}
		
	
		// session factory creates sessions
		private static SessionFactory sf = UtilityHibernate.getSessionFactory();
		
		@Override
		public Employee createEmployee(Employee employee) {
			try(Session sess = sf.openSession()){
			sess.beginTransaction();
			sess.save(employee);
			sess.getTransaction();
			sess.close();			
			return employee;
			}
		}

		
		@Override
		public Employee getEmployeeByID(int ID) {
			try(Session sess = sf.openSession()){
			Employee employee = sess.get(Employee.class, ID);
			sess.close();
			return employee;
			}
		}

		@Override
		public List<Employee> getEmployeeByEmail(String email) {
			try(Session sess = sf.openSession()){
				
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.like("email", email));
			List<Employee> employees = crit.list();
			return employees;
			}
		}
		
		@Override
		public List<Employee> getEmployeeByName(String name) {
			try(Session sess = sf.openSession()){
				Criteria crit = sess.createCriteria(Employee.class);
				crit.add(Restrictions.like("name", name));
				List<Employee> employees = crit.list();
				return employees;
				}
		}

		@Override
		public List<Employee> getAllEmployee() {
			try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			List<Employee> employees = crit.list();
			return employees;
			}
		}

		@Override
		public List<Employee> getAllManager() {
			try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.eq("isManager", true));
			List<Employee> employees = crit.list();
			return employees;
			}
		}

		@Override
		public List<Employee> getAllNotManager() {
			try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.eq("isManager", false));
			List<Employee> employees = crit.list();
			return employees;
			}
		}

		@Override
		public Employee updateEmployee(Employee employee) {
			try(Session sess = sf.openSession()){ 
			sess.beginTransaction(); 
			sess.update(employee);
			sess.getTransaction().commit(); 
			sess.close(); 
			return employee;
			}
		}

		@Override
		public boolean deleteEmployeeByID(int ID) {
			Transaction transaction = null;
			try(Session sess = sf.openSession()){
			Employee employee = sess.get(Employee.class, ID);
			transaction = sess.beginTransaction(); 
			sess.delete(employee);
			sess.getTransaction().commit(); 
			sess.close(); 

            if (transaction != null) {
                return true;
            }
            return false;
			}
		}

		@Override
		public boolean deleteEmployeeByEmail(String email) {
			Transaction transaction = null;
			try(Session sess = sf.openSession()){
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.like("email", email));
			List<Employee> employees = crit.list();
				
			Employee employee = employees.get(0);
			transaction = sess.beginTransaction(); 
			sess.delete(employee);
			sess.getTransaction().commit(); 
			sess.close(); 

            if (transaction != null) {
                return true;
            }
            return false;
			}
		}



}
