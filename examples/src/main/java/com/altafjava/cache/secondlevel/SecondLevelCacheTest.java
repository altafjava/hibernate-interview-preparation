package com.altafjava.cache.secondlevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import com.altafjava.util.HibernateUtil;

public class SecondLevelCacheTest {

	public static void main(String[] args) {
//		saveEmployee();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Statistics statistics = sessionFactory.getStatistics();
		statistics.setStatisticsEnabled(true);
		Session session = sessionFactory.openSession();

		int employeeId = 1;
		Employee employee = session.getReference(Employee.class, employeeId); // from DB
		System.out.println(employee);
		employee = session.getReference(Employee.class, employeeId); // from 1L cache
		System.out.println(employee);
		session.evict(employee);
		session.close();
		System.out.println("EntityFetchCount: " + statistics.getEntityFetchCount());
		System.out.println("2L cache hit count: " + statistics.getSecondLevelCacheHitCount());

		Session session2 = sessionFactory.openSession();
		employee = session2.getReference(Employee.class, employeeId);// from 2L cache
		System.out.println(employee);
		session2.evict(employee);
		employee = session2.getReference(Employee.class, employeeId); // from 2L cache
		System.out.println(employee);
		session2.close();
		System.out.println("EntityFetchCount: " + statistics.getEntityFetchCount());
		System.out.println("2L cache hit count: " + statistics.getSecondLevelCacheHitCount());

		Session session3 = sessionFactory.openSession();
		employee = session3.getReference(Employee.class, employeeId);// from 2L cache
		System.out.println(employee);
		session3.clear();
		employee = session3.getReference(Employee.class, employeeId); // from 2L cache
		System.out.println(employee);
		session3.close();
		System.out.println("EntityFetchCount: " + statistics.getEntityFetchCount());
		System.out.println("2L cache hit count: " + statistics.getSecondLevelCacheHitCount());
		HibernateUtil.closeSessionFactory();
	}

	private static void saveEmployee() {
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
	}

}
