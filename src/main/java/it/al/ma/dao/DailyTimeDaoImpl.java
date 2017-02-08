package it.al.ma.dao;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import it.al.ma.model.DailyTime;
import it.al.ma.model.User;
import it.al.ma.util.HibernateUtil;

public class DailyTimeDaoImpl implements DailyTimeDao {

	private SessionFactory sf = HibernateUtil.getSf();
	private Logger log = Logger.getLogger(DailyTimeDaoImpl.class);

	@Override
	public void insertDaily(DailyTime daily) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.save(daily);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("HibernateException during insert!", e);
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error("Exception during insert!", e);
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public void updateDaily(DailyTime daily) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.update(daily);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("HibernateException during update!", e);
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error("Exception during update!", e);
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public void deleteDaily(DailyTime daily) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.delete(daily);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("HibernateException during delete!", e);
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error("Exception during delete!", e);
		} finally {
			if (session != null)
				session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DailyTime> findByIdUser(User user, Date start, Date end) {
		Session session = null;
		Transaction tx = null;
		List<DailyTime> result = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(DailyTime.class);
			Criterion crBetweenDate = Restrictions.between("data", start, end);
			Criterion crUserJoin = Restrictions.eq("iduser", user.getId());
			LogicalExpression lxp = Restrictions.and(crBetweenDate, crUserJoin);
			cr.add(lxp);
			result = cr.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("HibernateException during select by UserId!", e);
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error("Exception during select by UserId!", e);
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

}
