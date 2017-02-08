package it.al.ma.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	@SuppressWarnings("deprecation")
	private static SessionFactory sf = new Configuration().configure().buildSessionFactory();

	private HibernateUtil() {
		// default
	}

	public static SessionFactory getSf() {
		return sf;
	}
}
