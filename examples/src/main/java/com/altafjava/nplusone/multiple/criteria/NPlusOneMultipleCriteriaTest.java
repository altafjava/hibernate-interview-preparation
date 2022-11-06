package com.altafjava.nplusone.multiple.criteria;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class NPlusOneMultipleCriteriaTest {
	public static void main(String[] args) throws InterruptedException {
//		saveEmployee();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> rootEmployee = criteriaQuery.from(Employee.class);
		rootEmployee.fetch("accounts", JoinType.LEFT);
		CriteriaQuery<Employee> selectEmployee = criteriaQuery.select(rootEmployee);
		Query<Employee> query = session.createQuery(selectEmployee);
		List<Employee> employees = query.list();
		for (Employee employee : employees) {
			System.out.print(employee.getFirstName() + ": ");
			List<Account> accounts = employee.getAccounts();
			for (Account account : accounts) {
				System.out.print(account.getAccountNo() + " ");
			}
			System.out.println();
		}
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	public static void saveEmployee() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 5; i++) {
			Employee employee = new Employee();
			employee.setFirstName("David");
			employee.setLastName("Warner");
			employee.setSalary(56789);
			Account account = new Account();
			account.setAccountNo("ACC" + i);
			account.setBranch("Australia" + i);
			account.setEmployee(employee);
			List<Account> accounts = new ArrayList<>();
			accounts.add(account);
			account = new Account();
			account.setAccountNo("BCC567" + i);
			account.setBranch("New Zealand" + i);
			accounts.add(account);
			employee.setAccounts(accounts);
			account.setEmployee(employee);
			session.persist(employee);
		}
		transaction.commit();
		session.close();
	}

}
