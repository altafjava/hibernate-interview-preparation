package com.altafjava.ordercolumn;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		CreditCard creditCard = new CreditCard();
		creditCard.setName("Samar");
		creditCard.setNumber("1234567890123456");
		CardTransaction cardTransaction1 = new CardTransaction();
		cardTransaction1.setAmount(567);
		cardTransaction1.setDate("2022-11-25");
		cardTransaction1.setCreditCard(creditCard);
		CardTransaction cardTransaction2 = new CardTransaction();
		cardTransaction2.setAmount(984);
		cardTransaction2.setDate("2021-04-18");
		cardTransaction2.setCreditCard(creditCard);
		creditCard.setCardTransactions(List.of(cardTransaction1, cardTransaction2));

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		session.persist(creditCard);
//		transaction.commit();

		CreditCard person2 = session.get(CreditCard.class, 1);
		System.out.println(person2);

		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
