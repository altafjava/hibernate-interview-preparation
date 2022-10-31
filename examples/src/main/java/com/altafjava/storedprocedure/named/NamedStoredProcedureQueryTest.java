package com.altafjava.storedprocedure.named;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import com.altafjava.util.HibernateUtil;

public class NamedStoredProcedureQueryTest {

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
		ProcedureCall procedureCall = session2.createNamedStoredProcedureQuery(Constant.NAMED_STORED_PROCEDURE_QUERY_NAME);
		List list = procedureCall.setParameter(Constant.COLUMN_NAME, 1200).getResultList();
		for (Object object : list) {
			System.out.println((Employee) object);
		}
		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
