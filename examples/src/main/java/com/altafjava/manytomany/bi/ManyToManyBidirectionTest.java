package com.altafjava.manytomany.bi;

import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class ManyToManyBidirectionTest {
	public static void main(String[] args) throws InterruptedException {
		Reader reader = buildReaderWithSubscription();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(reader);
		transaction.commit();
//		Reader reader2 = session.get(Reader.class, 1);
//		System.out.println(reader2);
//		List<Subscription> subscriptions = reader2.getSubscriptions();
//		for (Subscription subscription : subscriptions) {
//			System.out.println("read->sub: " + subscription);
//		}
//		Subscription subscription = session.get(Subscription.class, 2);
//		System.out.println("sub: " + subscription);
//		List<Reader> readers = subscription.getReaders();
//		if (readers != null) {
//			for (Reader reader3 : readers) {
//				System.out.println("sub->read: " + reader3);
//			}
//		} else {
//			System.out.println("sub->read: " + null);
//		}
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
