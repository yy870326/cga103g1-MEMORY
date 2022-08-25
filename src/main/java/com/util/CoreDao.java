package com.util;


import java.util.List;

import org.hibernate.Session;

public interface CoreDao <P, I> {
	
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	Integer insert(P pojo);

	Integer update(P pojo);

	P getOneById(I id);
	
	List<P> getAll();

}
