package com.niit.collaboration.daoimpl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Users;

@EnableTransactionManagement
@Repository
public class BlogDAOImpl implements BlogDAO {

	private static final Logger log =LoggerFactory.getLogger(BlogDAOImpl.class);
	
	@Autowired	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Constructor of BlogDAOImpl...
	 */
	public BlogDAOImpl() { 		
		
	}	
	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 *  Declare all CRUD Operations...
	 */
	
	@Transactional
	public boolean save(Blog blog){
		try {
			log.debug("**********Starting of save() method.");
			
			blog.setDatePosted(new Date(System.currentTimeMillis()));	//set current time as postDate
			blog.setStatus("N");	// N = New, R = Rejected, A = Approved 
			
			sessionFactory.getCurrentSession().save(blog);
			log.debug("**********End of save() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean update(Blog blog){
		try {
			log.debug("**********Starting of update() method.");
			sessionFactory.getCurrentSession().update(blog);
			log.debug("**********End of update() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveOrUpdate(Blog blog) {
		try {
			log.debug("**********Starting of saveOrUpdate() method.");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			log.debug("**********End of saveOrUpdate() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean delete(Blog blog) {
		try {
			log.debug("**********Starting of delete() method.");
			sessionFactory.getCurrentSession().delete(blog);
			log.debug("**********End of delete() method.");
			return true;
		} catch (Exception e) {
			log.error("Error occured : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public Blog get(int id) {
		log.debug("**********Starting of get() method.");
		String hql = "from Blog where id = " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Blog> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			log.debug("**********End of get() method.");
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Blog> list() {
		log.debug("**********Starting of list() method.");
		String hql = "from Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("**********End of list() method.");
		return query.list();
	}	

    
}