package com.altafjava.cache.firstlevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class EvictClearTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = new Employee();
		employee.setFirstName("David");
		employee.setLastName("Warner");
		employee.setSalary(1200);
		session.persist(employee);
		transaction.commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		Employee employee2 = session2.get(Employee.class, 1);
		System.out.println(employee2);
//		session2.evict(employee2);
		session2.clear();

		Employee employee3 = session2.get(Employee.class, 1);
		System.out.println(employee3);
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
