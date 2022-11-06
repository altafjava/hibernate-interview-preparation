package com.altafjava.nplusone.multiple.hql;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.altafjava.util.HibernateUtil;

public class NPlusOneMultipleHqlTest {
	public static void main(String[] args) throws InterruptedException {
//		saveEmployee();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String problemQuery = "FROM Employee e LEFT JOIN FETCH e.accounts"; // solution
//		String problemQuery = "FROM Employee e JOIN FETCH e.accounts"; // solution
//		String problemQuery = "FROM Employee"; // problem
		Query<Employee> fromQuery = session.createQuery(problemQuery, Employee.class);
		List<Employee> employees = fromQuery.list();
		for (Employee employee : employees) {
			List<Account> accounts = employee.getAccounts();
			System.out.println(employee);
			for (Account account : accounts) {
				System.out.println(account);
			}
			System.out.println();
		}
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	public static void saveEmployee() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee();
			employee.setFirstName("David");
			employee.setLastName("Warner");
			employee.setSalary(1000 * i);
			Account account = new Account();
			account.setAccountNo("ACC" + i);
			account.setBranch("Australia" + i);
			account.setEmployee(employee);
			List<Account> accounts = new ArrayList<>();
			accounts.add(account);
			account = new Account();
			account.setAccountNo("BCC567" + i);
			account.setBranch("New Zealand" + i);
			accounts.add(account);
			employee.setAccounts(accounts);
			account.setEmployee(employee);
			session.persist(employee);
		}
		transaction.commit();
		session.close();
	}

}
