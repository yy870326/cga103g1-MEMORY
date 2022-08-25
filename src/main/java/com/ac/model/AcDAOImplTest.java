package com.ac.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.util.CoreDao;
import com.util.HibernateUtil;

public class AcDAOImplTest implements CoreDao<AcVO, Integer> {
	
	public static final String GET_ALL = "FROM AcVo"; 
	
	@Override
	public Integer insert(AcVO acVo) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			final Integer id = (Integer) session.save(acVo);
			transaction.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}		
	}

	@Override
	public Integer update(AcVO acVo) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			AcVO ac = session.get(AcVO.class, acVo.getAc_no());
			ac.setAc_content(acVo.getAc_content());
			ac.setAc_title(acVo.getAc_title());
			ac.setAc_time(acVo.getAc_time());
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}		
	}
	

	@Override
	public AcVO getOneById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			AcVO acVo = session.get(AcVO.class, id);
			transaction.commit();
			return acVo;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}		
	}

	@Override
	public List<AcVO> getAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<AcVO> query = session.createQuery
					("FROM AcVo", AcVO.class);
			List<AcVO> list = query.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}		
	}

	
	public static void main(String[] args) {
		AcDAOImplTest acDAOImplTest = new AcDAOImplTest();
		AcVO acVo = new AcVO();

		// GetAll
//		acDAOImplTest.getAll().forEach(System.out::println);
		
		// Get One
		System.out.println(acDAOImplTest.getOneById(6));
		
		// Insert
//		acVo.setMem_no(3);
//		acVo.setAc_type_no(7);
//		acVo.setAc_content("好冷");
//		acVo.setAc_title("陽明山");
//		acVo.setAc_time(LocalDateTime.of(2022, 8, 23, 14, 30));
//		acVo.setAc_update(0);	
//		System.out.println(acDAOImplTest.insert(acVo));
			
		// Update
//		acVo.setAc_no(5);
//		acVo.setAc_content("非常的冷");
//		acVo.setAc_title("陽明山上");
//		acVo.setAc_time(LocalDateTime.of(2022, 8, 30, 14, 30));
//		System.out.println(acDAOImplTest.update(acVo));

		
		
	}

	

}
