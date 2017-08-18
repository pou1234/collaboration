package com.niit.collaboration.daoimpl;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.Users;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger Log =LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	
	public UserDAOImpl(SessionFactory sessionFactory){
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e){
		 Log.error("Unable to connect to db");
			e.printStackTrace();
		}
	}
    
	@Transactional
	public List<Users> list() {
		Log.debug("->->Starting of the method list");
        @SuppressWarnings("unchecked")
        List<Users> list = (List<Users>) sessionFactory.getCurrentSession().createCriteria(Users.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
       return list;
	}
    
	@Transactional
	public Users get(String id, String password) {
        String hql="from Users where id='" + id + "' and" + "password ='" + password + "'";
		
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> list = query.list();
		if(list==null ||list.size()==0 ){
			return null;
		}
		else{
			return list.get(0);
		}		
	
		}

	@Transactional
	public Users get(String id) {
     String hql = " from Users where id = " + "'"	+ id + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> list = query.list();
		if(list==null ||list.size()==0 ){
			return null;
		}
		else{
			return list.get(0);
		}		
	
	}

	@Transactional
	public boolean save(Users userDetailsDetails) {
		try {			
			//userDetailsDetails.setRole("ROLE_USER");
			//userDetailsDetails.setEnabled(true);
			userDetailsDetails.setIsOnline("N");
			sessionFactory.getCurrentSession().save(userDetailsDetails);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean update(Users userDetailsDetails) {
		try {			
			sessionFactory.getCurrentSession().update(userDetailsDetails);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
    
    
    @Transactional
	public void setOnline(String userID) {
	    Log.debug("Starting of the method setOnline");
	    String hql="UPDATE Users SET isOnline= 'Y' where id='" + userID + "'";
	     Log.debug("hql:" + hql);
	     Query query=sessionFactory.getCurrentSession().createQuery(hql);
	       query.executeUpdate();
	     Log.debug("Ending of the method setOnline");
	     
    	}
    @Transactional
	public void setOffline(String userID) {
    	 Log.debug("Starting of the method setOffline");
 	    String hql="UPDATE Users SET isOnline= 'Y' where id='" + userID + "'";
 	     Log.debug("hql:" + hql);
 	     Query query=sessionFactory.getCurrentSession().createQuery(hql);
 	       query.executeUpdate();
 	     Log.debug("Ending of the method setOffline");
		
	}


		@Transactional
		public boolean delete(Users userDetailsDetails) {
			try {
				sessionFactory.getCurrentSession().delete(userDetailsDetails);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
	    @Transactional
		public Users authenticate(String id, String password) {
			Log.debug("->->Starting of the method isValidUserDetails");
			String hql="from Users where id='" + id + "' and " + "password ='" + password + "'";
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Users> list=(List<Users>) query.list();
			
			if(list != null && !list.isEmpty()){
				return list.get(0);
			}
			return null;
		}

	    
	    @Transactional
		public List<Users> getAllUsers() {
	    	Log.debug("->->Starting of the method list");
	        @SuppressWarnings("unchecked")
	        List<Users> getAllUsers = (List<Users>) sessionFactory.getCurrentSession().createCriteria(Users.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	       return getAllUsers;
	    	
	    	
			
		}
		
	




	}


	
	

