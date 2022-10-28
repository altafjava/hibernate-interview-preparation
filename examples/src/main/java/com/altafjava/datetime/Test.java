package com.altafjava.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		LocalDate date = LocalDate.of(1990, 12, 25);
		LocalTime time = LocalTime.of(10, 35);
		LocalDateTime timestamp = LocalDateTime.of(1989, 11, 24, 9, 46);

		Article article = new Article();
		article.setTitle("Title");
		article.setContent("Content");
		article.setDate(new Date());
		article.setCalendar(Calendar.getInstance());
		article.setUpdatedDate(date);
		article.setUpdatedTime(time);
		article.setPublishedTimestamp(timestamp);

		session.persist(article);
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
