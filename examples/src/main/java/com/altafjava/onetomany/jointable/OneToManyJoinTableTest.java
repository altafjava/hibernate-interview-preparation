package com.altafjava.onetomany.jointable;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.onetomany.jointable.entity.Account;
import com.altafjava.onetomany.jointable.entity.Employee;
import com.altafjava.util.HibernateUtil;

public class OneToManyJoinTableTest {
	public static void main(String[] args) throws InterruptedException {
		Employee employee = buildEmployeeWithAccount();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(employee);
		transaction.commit();
//		Employee employee = session.get(Employee.class, 1);
//		System.out.println(employee);
//		Account account2 = employee2.getAccount();
//		System.out.println(account2);
//		System.out.println("Acc Emp: " + account2.getEmployee());
		session.close();
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
		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		account = new Account();
		account.setAccountNo("BCC567");
		account.setBranch("New Zealand");
		accounts.add(account);
		employee.setAccounts(accounts);
		return employee;
	}

}
