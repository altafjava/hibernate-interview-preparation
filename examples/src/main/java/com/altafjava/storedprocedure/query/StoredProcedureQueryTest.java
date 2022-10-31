package com.altafjava.storedprocedure.query;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.ParameterMode;

public class StoredProcedureQueryTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 1; i <= 2; i++) {
			Employee employee = new Employee();
			employee.setFirstName("David" + i);
			employee.setLastName("Warner" + i);
			employee.setSalary(1200);
			session.persist(employee);
		}
		transaction.commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		ProcedureCall procedureCall = session2.createStoredProcedureQuery("getEmployeeDetailsBySalary");
		procedureCall.registerParameter("salary", Double.class, ParameterMode.IN);
		procedureCall.setParameter("salary", 1200);
		List<Object[]> procedureOutputs = procedureCall.getResultList();
		for (Object[] columns : procedureOutputs) {
			System.out.println(columns[0] + " " + columns[1] + " " + columns[2] + " " + columns[3]);
		}
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
