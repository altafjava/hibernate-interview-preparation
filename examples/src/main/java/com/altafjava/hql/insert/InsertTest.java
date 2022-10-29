package com.altafjava.hql.insert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.Query;

public class InsertTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String qry = "insert into Person(firstName, lastName)" + "values(:firstName, :lastName)";
		Query query = session.createQuery(qry);
		query.setParameter("firstName", "Moin");
		query.setParameter("lastName", "Ali");
		int noOfRowsAffected = query.executeUpdate();
		transaction.commit();
		System.out.println("noOfRowsAffected: " + noOfRowsAffected);
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
