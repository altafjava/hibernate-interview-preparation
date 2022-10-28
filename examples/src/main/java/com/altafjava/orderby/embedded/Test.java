package com.altafjava.orderby.embedded;

import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		Zipcode zipcode1 = new Zipcode();
		zipcode1.setAreaCode(51);
		zipcode1.setZip("765478");
		Address address1 = new Address();
		address1.setCity("Hyderabad");
		address1.setState("Telangana");
		address1.setStreet("Tiger chowk");
		address1.setZipcode(zipcode1);

		Zipcode zipcode2 = new Zipcode();
		zipcode2.setAreaCode(24);
		zipcode2.setZip("123456");
		Address address2 = new Address();
		address2.setCity("Bengaluru");
		address2.setState("Karnataka");
		address2.setStreet("Howra bridge");
		address2.setZipcode(zipcode2);

		Zipcode zipcode3 = new Zipcode();
		zipcode3.setAreaCode(24);
		zipcode3.setZip("987654");
		Address address3 = new Address();
		address3.setCity("Chennai");
		address3.setState("Tamilnadu");
		address3.setStreet("Sonpapri");
		address3.setZipcode(zipcode3);

		Person person = new Person();
		person.setName("Ajay");
		person.setAddresses(Set.of(address1, address2, address3));

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		session.persist(person);
//		transaction.commit();
		
		Person person2 = session.get(Person.class, 1);
		System.out.println(person2);
		
		session.close();
		HibernateUtil.closeSessionFactory();
	}

}
