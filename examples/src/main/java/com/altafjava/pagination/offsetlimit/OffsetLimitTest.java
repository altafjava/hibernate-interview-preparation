package com.altafjava.pagination.offsetlimit;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.altafjava.util.HibernateUtil;

public class OffsetLimitTest {

	public static void main(String[] args) {
//		saveEmployees(10);
		Session session = HibernateUtil.getSessionFactory().openSession();
		String q = "from Employee";
		Query<Employee> query = session.createQuery(q);
		query.setFirstResult(2);// start index
		query.setMaxResults(5);// max rows
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	public static void saveEmployees(int noOfEmplloyees) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= noOfEmplloyees; i++) {
			Employee employee = new Employee();
			employee.setFirstName("David" + i);
			employee.setLastName("Warner" + i);
			employee.setSalary(1200 * i);
			session.persist(employee);
		}
		transaction.commit();
		session.close();
	}

}
