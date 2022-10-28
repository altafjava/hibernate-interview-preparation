package com.altafjava.elementcollection;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		State state = new State();
		state.setName("Telangana");
		List<String> cities = List.of("Hyderabad", "Secundarabad", "Nalgonda");
		state.setCities(cities);

		session.persist(state);
		transaction.commit();
//		State state2 = session.get(State.class, 1);
//		System.out.println(state2);
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
