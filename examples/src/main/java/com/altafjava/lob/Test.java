package com.altafjava.lob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Article article = new Article();
		article.setTitle("Title");
		article.setContent("A very long article content");
		session.persist(article);
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
