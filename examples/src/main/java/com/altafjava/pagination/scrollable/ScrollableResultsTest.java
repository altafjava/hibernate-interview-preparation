package com.altafjava.pagination.scrollable;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.altafjava.util.HibernateUtil;

public class ScrollableResultsTest {

	public static void main(String[] args) {
//		OffsetLimitTest.saveEmployees(24);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
//		String query="from Employee e ORDER BY e.id DESC";
		String query = "from Employee";
		Query<Employee> fromQuery = session.createQuery(query, Employee.class);
		try (ScrollableResults<Employee> scrollableResults = fromQuery.scroll()) {
			while (scrollableResults.next() && scrollableResults.getRowNumber() >= 0 && scrollableResults.getRowNumber() < 10) {
				Employee employee = scrollableResults.get();
				System.out.println(employee);
			}
		}
		session.close();
		HibernateUtil.closeSessionFactory();
	}
}
