package com.altafjava.batch.update;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.altafjava.util.HibernateUtil;

public class BatchUpdateTest {

	public static void main(String[] args) {
		boolean isSave = false;
		if (isSave) {
			saveEmployee();
		} else {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<Employee> fromQuery = session.createQuery("from Employee", Employee.class);
			fromQuery.setCacheMode(CacheMode.IGNORE);
			fromQuery.scroll(ScrollMode.FORWARD_ONLY);
			ScrollableResults<Employee> scrollableResults = fromQuery.scroll();
			int count = 0;
			while (scrollableResults.next()) {
				++count;
				Employee employee = scrollableResults.get();
				employee.setFirstName(employee.getFirstName() + count);
				employee.setLastName(employee.getLastName() + count);
				employee.setSalary(employee.getSalary() * count);
				if (count % 5 == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
			session.close();
		}
		HibernateUtil.closeSessionFactory();
	}

	private static void saveEmployee() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee();
			employee.setFirstName("David" + i);
			employee.setLastName("Warner" + i);
			employee.setSalary(1000 * i);
			session.persist(employee);
		}
		transaction.commit();
		session.close();
	}

}
