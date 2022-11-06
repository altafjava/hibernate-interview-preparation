package com.altafjava.nplusone.single;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class NPlusOneSingleTest {
	public static void main(String[] args) throws InterruptedException {
//		saveEmployee();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class, 1);
		System.out.println(employee);
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	public static void saveEmployee() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = new Employee();
		employee.setFirstName("David");
		employee.setLastName("Warner");
		employee.setSalary(56789);
		Account account = new Account();
		account.setAccountNo("ACC");
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
		session.persist(employee);
		transaction.commit();
		session.close();
	}

}
