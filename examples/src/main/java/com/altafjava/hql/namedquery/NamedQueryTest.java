package com.altafjava.hql.namedquery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.Query;

public class NamedQueryTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 2; i++) {
			Employee employee = new Employee();
			employee.setFirstName("David" + i);
			employee.setLastName("Warner" + i);
			employee.setSalary(1000 * i);
			session.persist(employee);
		}
		transaction.commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		Query query = session2.createNamedQuery(Constant.GET_EMPLOYEE_BY_ID_NAME);
		query.setParameter("id", 2);
		Employee employee = (Employee) query.getSingleResult();
		System.out.println(employee);
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
