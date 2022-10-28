package com.altafjava.util;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.PersistContext;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import com.altafjava.onetoone.foreign.Employee;

public class PersistEventListenerImpl implements PersistEventListener {

	@Override
	public void onPersist(PersistEvent persistEvent) throws HibernateException {
		System.out.println("onPersist(PersistEvent persistEvent)");
		Employee employee = (Employee) persistEvent.getObject();
		System.out.println("onPersist Employee: " + employee);
		if (employee != null) {
			employee.setFirstName("Sanam");
		}
	}

	@Override
	public void onPersist(PersistEvent event, PersistContext createdAlready) throws HibernateException {
		System.out.println("onPersist(PersistEvent event, PersistContext createdAlready)");
	}
}
