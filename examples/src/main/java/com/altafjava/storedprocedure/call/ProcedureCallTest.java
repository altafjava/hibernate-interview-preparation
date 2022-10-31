package com.altafjava.storedprocedure.call;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import com.altafjava.util.HibernateUtil;
import jakarta.persistence.ParameterMode;

public class ProcedureCallTest {

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
		ProcedureCall procedureCall = session2.createStoredProcedureCall("getEmployeeById");
		procedureCall.registerParameter(1, Integer.class, ParameterMode.IN);
		procedureCall.setParameter(1, 2); // 1st 1 is positional parameter & 2nd 1 is the employee id
		procedureCall.registerParameter(2, String.class, ParameterMode.OUT);
		procedureCall.registerParameter(3, String.class, ParameterMode.OUT);
		procedureCall.registerParameter(4, Double.class, ParameterMode.OUT);
		ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
		Object firstName = procedureOutputs.getOutputParameterValue(2);
		Object lastName = procedureOutputs.getOutputParameterValue(3);
		Object salary = procedureOutputs.getOutputParameterValue(4);
		System.out.println(firstName + " " + lastName + " " + salary);

//		procedureCall.registerParameter("eid", Integer.class, ParameterMode.IN);
//		procedureCall.setParameter("eid", 2);
//		procedureCall.registerParameter("firstName", String.class, ParameterMode.OUT);
//		procedureCall.registerParameter("lastName", String.class, ParameterMode.OUT);
//		procedureCall.registerParameter("salary", Double.class, ParameterMode.OUT);
//		ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
//		Object firstName = procedureOutputs.getOutputParameterValue("firstName");
//		Object lastName = procedureOutputs.getOutputParameterValue("lastName");
//		Object salary = procedureOutputs.getOutputParameterValue("salary");
//		System.out.println(firstName + " " + lastName + " " + salary);

		session2.close();
		HibernateUtil.closeSessionFactory();
	}

}
