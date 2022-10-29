package com.altafjava.hql.delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.Query;

public class DeleteTest {

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
		Transaction transaction2 = session2.beginTransaction();
		String qry = "deLETE from Employee where eid=:id";	// query is in case-sensitive
		Query query = session2.createQuery(qry);
		query.setParameter("id", 1);
		int noOfRowsAffected = query.executeUpdate();
		transaction2.commit();
		System.out.println("noOfRowsAffected: " + noOfRowsAffected);
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
