package com.altafjava.manytomany.uni;

import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class ManyToManyUnidirectionTest {
	public static void main(String[] args) throws InterruptedException {
		Reader reader = buildReaderWithSubscription();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(reader);
		transaction.commit();
//		Employee employee = session.get(Employee.class, 1);
//		System.out.println(employee);
//		Account account2 = employee2.getAccount();
//		System.out.println(account2);
//		System.out.println("Acc Emp: " + account2.getEmployee());
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	public static Reader buildReaderWithSubscription() {
		Subscription subscription1 = new Subscription();
		subscription1.setSubscriptionName("Youtube");
		Subscription subscription2 = new Subscription();
		subscription2.setSubscriptionName("Netflix");
		List<Subscription> subscriptions = Arrays.asList(subscription1, subscription2);
		Reader reader = new Reader();
		reader.setEmail("abc@gmail.com");
		reader.setReaderName("David");
		reader.setSubscriptions(subscriptions);
		return reader;
	}

}
