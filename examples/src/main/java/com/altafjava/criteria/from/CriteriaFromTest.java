package com.altafjava.criteria.from;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import com.altafjava.util.HibernateUtil;

public class CriteriaFromTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 2; i++) {
			Employee employee = new Employee();
			employee.setFirstName("David" + i);
			employee.setLastName("Warner" + i);
			employee.setSalary(1000 * i);
			session.persist(employee);
		}
		transaction.commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		HibernateCriteriaBuilder criteriaBuilder = session2.getCriteriaBuilder();
		JpaCriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		JpaRoot<Employee> fromEmployee = criteriaQuery.from(Employee.class);
//		criteriaQuery.where(criteriaBuilder.greaterThan(fromEmployee.get("eid"), 1));
		JpaCriteriaQuery<Employee> select = criteriaQuery.select(fromEmployee);
//		select.where(criteriaBuilder.greaterThan(fromEmployee.get("eid"), 1));
		Query<Employee> query = session2.createQuery(select);
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
