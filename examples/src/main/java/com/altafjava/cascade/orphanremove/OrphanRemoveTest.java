package com.altafjava.cascade.orphanremove;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class OrphanRemoveTest {
	public static void main(String[] args) throws InterruptedException {
		Employee employee = buildEmployeeWithAccount();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session1 = sessionFactory.openSession();
		Transaction transaction1 = session1.beginTransaction();
		session1.persist(employee);
		transaction1.commit();
		session1.close();
		
		Session session2 = sessionFactory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		Employee employee2 = session2.get(Employee.class, 1);
		List<Account> accounts = employee2.getAccounts();
		accounts.remove(0); // remove single account from list of accounts
		transaction2.commit();
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

	public static Employee buildEmployeeWithAccount() {
		Employee employee = new Employee();
		employee.setFirstName("David");
		employee.setLastName("Warner");
		employee.setSalary(56789);
		Account account = new Account();
		account.setAccountNo("ACC123");
		account.setBranch("Australia");
		account.setEmployee(employee);
		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		account = new Account();
		account.setAccountNo("BCC567");
		account.setBranch("New Zealand");
		accounts.add(account);
		employee.setAccounts(accounts);
		account.setEmployee(employee);
		return employee;
	}

}
