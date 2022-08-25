package com.util;

import static com.util.HibernateUtil.*;

import org.hibernate.Transaction;
import org.hibernate.Session;

public interface CoreService {

	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	default Transaction beginTransacion() {
		return HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	}
	
	default void commit() {
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}
	
	default void rollback() {
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
	}
	
	
}
