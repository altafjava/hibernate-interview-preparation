package com.altafjava.util;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	static {
//		BootstrapServiceRegistryBuilder bootstrapServiceRegistryBuilder = new BootstrapServiceRegistryBuilder();
//		bootstrapServiceRegistryBuilder.applyIntegrator(new IntegratorImpl());
//		BootstrapServiceRegistry bootstrapServiceRegistry = bootstrapServiceRegistryBuilder.build();
//		StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder(bootstrapServiceRegistry).configure().build();
		
//		Map<String, Object> dataSourceMap = new LinkedHashMap<>();
//		dataSourceMap.put(Environment.DATASOURCE, getProxyDataSource());
//		StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().applySettings(dataSourceMap).build();
		
		StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources().getMetadataBuilder(standardServiceRegistry).build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}

	private static DataSource getProxyDataSource() {
		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/test?createDatabaseIfNotExist=true");
		mysqlDataSource.setUser("root");
		mysqlDataSource.setPassword("root");

		ProxyDataSource proxyDataSource = ProxyDataSourceBuilder.create(mysqlDataSource).asJson().countQuery().logQueryToSysOut()
				.multiline().build();
		return proxyDataSource;
	}

}
