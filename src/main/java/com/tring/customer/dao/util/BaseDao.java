package com.tring.customer.dao.util;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * @author akula
 *
 */
public abstract class BaseDao {

	@Autowired
	@Qualifier("customerHibernateTemplate")
	HibernateTemplate hibernateTemplate;

	protected Session getCurrentSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	protected Session getSession() {
		return hibernateTemplate.getSessionFactory().openSession();
	}

	public <T> void persist(T entity) {
		getCurrentSession().persist(entity);
	}

	public <T> Object saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	public <T> Object save(T entity) {
		getCurrentSession().save(entity);
		return entity;
	}

	public <T> void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public <T> Object merge(T entity) {
		getCurrentSession().merge(entity);
		return entity;
	}
}
