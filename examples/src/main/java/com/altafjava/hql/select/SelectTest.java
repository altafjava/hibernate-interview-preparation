package com.altafjava.hql.select;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.Query;

public class SelectTest {

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
		Query query = session2.createQuery("from Employee as e"); // e is alias of employee
		List<Employee> employees = query.getResultList();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		session2.close();

		Session session3 = sessionFactory.openSession();
		String q = "select emp.firstName, emp.salary from Employee emp"; // emp is Alias name of employee
		query = session3.createQuery(q);
//		Query query = session3.createQuery("select firstName from Employee");
		List<Object> employeeList = query.getResultList();
		if (!employeeList.isEmpty()) {
			if (employeeList.get(0).getClass().isArray()) {
				for (Object object : employeeList) {
					for (Object cell : (Object[]) object) {
						System.out.print(cell + " ");
					}
					System.out.println();
				}
			} else {
				for (Object column : employeeList) {
					System.out.println(column);
				}
			}
		}
		session3.close();
		HibernateUtil.closeSessionFactory();
	}

}
