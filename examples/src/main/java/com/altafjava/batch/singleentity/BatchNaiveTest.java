package com.altafjava.batch.singleentity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class BatchNaiveTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 10; i++) {
			System.out.println("Statement Queued : " + i);
			Employee employee = new Employee();
			employee.setEid(i);
			employee.setFirstName("David" + i);
			employee.setLastName("Warner" + i);
			employee.setSalary(1000 * i);
			session.persist(employee);
		}
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
