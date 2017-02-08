package it.al.ma.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sf;
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory getSf() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sf = configuration.buildSessionFactory(serviceRegistry);
	    return sf;
	}
	
	private static HibernateUtil instance;

	private HibernateUtil() {
		//default
	}

	/**
	 * Singleton application is not correct as there is no need for any instance
	 * of the HibernateUtil class, since there are no declared instanced variables
	 * at the moment.
	 */
//	public static HibernateUtil getInstance() {
//		if(instance == null) 
//			instance = new HibernateUtil();
//
//		return instance;
//	}
}
