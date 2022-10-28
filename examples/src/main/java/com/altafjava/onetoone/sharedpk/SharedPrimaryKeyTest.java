package com.altafjava.onetoone.sharedpk;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.altafjava.onetoone.sharedpk.entity.Account;
import com.altafjava.onetoone.sharedpk.entity.Employee;
import com.altafjava.util.HibernateUtil;

public class SharedPrimaryKeyTest {
	public static void main(String[] args) throws InterruptedException {
		Employee employee = buildEmployeeWithAccount();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(employee);
		transaction.commit();
		Employee employee2 = session.get(Employee.class, 1);
		System.out.println(employee2);
		Account account2 = employee2.getAccount();
		System.out.println(account2);
//		System.out.println("Acc Emp: " + account2.getEmployee());
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	public static Employee buildEmployeeWithAccount() {
		Employee employee = new Employee();
		employee.setFirstName("David");
		employee.setLastName("Warner");
		employee.setSalary(56789);
		Account account = new Account();
		account.setAccountNo("ACC123");
		account.setBranch("Australia");
		employee.setAccount(account);
//		account.setEmployee(employee);
		return employee;
	}

}
