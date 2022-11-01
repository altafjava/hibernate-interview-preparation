package com.altafjava.cache.querylevel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.altafjava.util.HibernateUtil;

public class QueryCacheTest {

	public static void main(String[] args) {
		saveEmployee();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		String q = "from Employee where eid=:id and firstName=:name";
		Query<Employee> query = session.createQuery(q);
		query.setParameter("id", 1);
		query.setParameter("name", "David");
		query.setCacheable(true);
		Employee employee = query.uniqueResult();
		System.out.println(employee);

		query = session.createQuery(q);
		query.setParameter("id", 1);
		query.setParameter("name", "David");
		query.setCacheable(true);
		employee = query.uniqueResult();
		System.out.println(employee);

		query = session.createQuery(q);
		query.setCacheable(true);
		query.setParameter("id", 1);
		query.setParameter("name", "David");
		employee = query.uniqueResult();
		System.out.println(employee);
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
