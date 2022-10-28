package com.altafjava.util;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.BootstrapContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class IntegratorImpl implements Integrator {

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
//		System.out.println("integrate(MSS)");
//		EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
//		eventListenerRegistry.prependListeners(EventType.PERSIST, PersistEventListenerImpl.class);
	}

	@Override
	public void integrate(Metadata metadata, BootstrapContext bootstrapContext, SessionFactoryImplementor sessionFactory) {
//		System.out.println("integrate(MBS)");
//		EventListenerRegistry eventListenerRegistry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
//		eventListenerRegistry.prependListeners(EventType.PERSIST, PersistEventListenerImpl.class);
//		Integrator.super.integrate(metadata, bootstrapContext, sessionFactory);
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
//		System.out.println("disintegrate");
	}
}
