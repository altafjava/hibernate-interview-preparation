package com.altafjava.batch.multipleentities;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class BatchInsertMultipleEntitiesTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 10; i++) {
			System.out.print("Employee Statement Queued: " + i + ", ");
			List<Account> accounts = new ArrayList<>();
			Employee employee = new Employee();
			employee.setEid(i);
			employee.setFirstName("David" + i);
			employee.setLastName("Warner" + i);
			employee.setSalary(1000 * i);
			for (int j = i * 10; j < i * 10 + 3; j++) {
				System.out.print(" Account:" + j);
				Account account = new Account();
				account.setAid(j);
				account.setAccountNo("ACC" + i * j);
				account.setBranch("Branch" + i * j);
				account.setEmployee(employee);
				accounts.add(account);
			}
			System.out.println();
			employee.setAccounts(accounts);
			session.persist(employee);
			if (i % 5 == 0) { // same as the JDBC batch size
				session.flush(); // flush a batch of inserts and release memory:
				session.clear();
			}
		}
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
