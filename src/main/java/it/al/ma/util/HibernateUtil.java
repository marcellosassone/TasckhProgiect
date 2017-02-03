package it.al.ma.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf = new Configuration().configure().buildSessionFactory();

	private static HibernateUtil instance;
	
	private HibernateUtil() {
		//default
	}
	
	public static HibernateUtil getInstance() {
		if(instance == null) 
			instance = new HibernateUtil();
		
		return instance;
	}
	
	public static SessionFactory getSf() {
		return sf;
	}
	
}
