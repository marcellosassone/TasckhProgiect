package it.al.ma.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import it.al.ma.model.Country;
import it.al.ma.model.User;
import it.al.ma.util.HibernateUtil;




public class UserDaoImpl implements UserDao{
	private SessionFactory sf = HibernateUtil.getInstance().getSf();
	private Logger log = Logger.getLogger(UserDaoImpl.class);
	
	
	@Override
	public void insertUser(User user) {
		Session session=null;
		Transaction tx=null;
		try {
		session=sf.openSession();
		tx=session.beginTransaction();
		session.save(user);
		tx.commit();
	
	} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		log.error(e.getStackTrace());
	} finally {
		if (session != null)
			session.close();
	}
	}
	
	
	
	@Override
	public void updateUser(User user) {
		Session session=null;
		Transaction tx=null;
		try {
		session=sf.openSession();
		tx=session.beginTransaction();
		
		session.update(user);
		tx.commit();
	
	} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		log.error("errore finalizza modifica: ", e);
	} finally {
		if (session != null)
			session.close();
	}
		
		
	}

	@Override
	public void deleteUser(User user) {
		Session session=null;
		Transaction tx=null;
		try {
		session=sf.openSession();
		tx=session.beginTransaction();
		session.delete(user);
		tx.commit();
	
	} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		log.error(e.getStackTrace());
	} finally {
		if (session != null)
			session.close();
	}
		
	}

	@Override
	public User findByIdUser(User user) {
		Session session = null;
		Transaction tx = null;
		User user1 = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(User.class);
			Criterion crit1 = Restrictions.eq("id", user.getId());
			
			cr.add(crit1);
			user1 = (User) cr.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return user1;
	}

	@Override
	public User findByMailAndPassword(User user) {
		Session session = null;
		Transaction tx = null;
		User user1 = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(User.class);
			Criterion crit1 = Restrictions.eq("email", user.getEmail());
			Criterion crit2 = Restrictions.eq("password", user.getPassword());
			LogicalExpression lx = Restrictions.and(crit1, crit2);
			cr.add(lx);
			user1 = (User) cr.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return user1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		Session session = null;
		Transaction tx = null;
		List<User> listaUser = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("FROM User");
			listaUser = qry.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return listaUser;
		
	}
	
	public Map<Integer,String> getCountryMap(){
		
		Map<Integer,String>mapCountry=new HashMap<>();
		Session session = null;
		Transaction tx = null;
		List<Country> listaCountry = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("FROM Country");
			listaCountry = qry.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		
		for(Country c:listaCountry){
			mapCountry.put(c.getIdcountry(), c.getCountryname());
			
			
		}
		
		
		
		
		return mapCountry;
		
		
		
	}

}
