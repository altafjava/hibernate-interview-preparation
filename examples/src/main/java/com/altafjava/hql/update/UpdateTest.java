package com.altafjava.hql.update;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.Query;

public class UpdateTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = new Employee();
		employee.setFirstName("David");
		employee.setLastName("Warner");
		employee.setSalary(56789);
		session.persist(employee);
		transaction.commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		Query query = session2.createQuery("UPDATE Employee set firstName=:name where eid=:id");
		query.setParameter("name", "Finch");
		query.setParameter("id", 1);
		int noOfRowsAffected = query.executeUpdate();
		transaction2.commit();
		System.out.println("noOfRowsAffected: " + noOfRowsAffected);
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
