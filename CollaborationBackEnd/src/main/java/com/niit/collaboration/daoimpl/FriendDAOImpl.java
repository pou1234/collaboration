package com.niit.collaboration.daoimpl;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Users;

@Repository
public class FriendDAOImpl implements FriendDAO {

	private static final Logger Log =LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	
	public FriendDAOImpl(SessionFactory sessionFactory){
		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e){
		 Log.error("Unable to connect to db");
			e.printStackTrace();
		}
	}

   

    @Transactional
	public Friend get(String userId, String friendId) {
		
	        String hql="from Friend where id='" + userId + "' and " + " status = '" + friendId+ "'";
			
	        Query query = sessionFactory.getCurrentSession().createQuery(hql);
	        Log.debug("hql :"+ hql);
	        
	        return (Friend)query.uniqueResult();

	}

    @Transactional
	public boolean save(Friend friend) {
		try {			
			//friendDetailsDetails.setRole("ROLE_USER");
			//friendDetailsDetails.setEnabled(true);
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

    @Transactional
	public boolean update(Friend friend) {
		try {			
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			}

   
    @Transactional
  public List<Friend> getMyFriends(String userId)
  {
    String hql = "FROM Friend WHERE (userID='"+userId+"'  AND status = 'A')  or ( friendID = '" + userId + "' and status = 'A')";
    Query query = sessionFactory.getCurrentSession().createQuery(hql);
    
     List<Friend> list = (List<Friend>)query.list();
     
      return list;
      
  }
          
    @Transactional
	public void setIsOnline(String friendId) {
		Log.debug("Starting of the method setOnline");
	    String hql="UPDATE Friend SET isOnline= 'Y' where id='" + friendId + "'";
	     Log.debug("hql:" + hql);
	     Query query=sessionFactory.getCurrentSession().createQuery(hql);
	       query.executeUpdate();
	     Log.debug("Ending of the method setOnline");
	     
		
	}

    @Transactional
	public void setOffline(String friendId) {
		 Log.debug("Starting of the method setOffline");
	 	    String hql="UPDATE User SET isOnline= 'Y' where id='" + friendId + "'";
	 	     Log.debug("hql:" + hql);
	 	     Query query=sessionFactory.getCurrentSession().createQuery(hql);
	 	       query.executeUpdate();
	 	     Log.debug("Ending of the method setOffline");
			
		
	}

   @Override
	public boolean isFriend(String userId, String friendId) {
	   Log.debug("**********Starting of isFriend() method.");
		
		String hql = "from Friend where (userId = '" + userId +"' and friendId = '"+ friendId +"') or (friendId = '" + userId +"' and userId = '"+ friendId +"')";
		Session session=sessionFactory.openSession();
		Query query = session.createQuery(hql);
		
		List<Friend> list = query.list();
		
		if (list != null && !list.isEmpty()) {
			Log.debug("**********End of isFriend() method.");
			//session.flush();
			//session.close();
			return true;
		}
		Log.debug("**********End of isFriend() method.");
		return false;
	}
		
	
	@Transactional
	public void rejectFriend(String userId) {
		Log.debug("Starting of the method rejectFriend");
		String hql = "update Friend set status = 'R' where friendId = '" + userId + "'";
	     Log.debug("hql:" + hql);
	     Query query=sessionFactory.getCurrentSession().createQuery(hql);
	       query.executeUpdate();
	     Log.debug("Ending of the method rejectFriend");
	     
	
		
	}
    @Transactional
	public Friend getSelectedFriend(String userId, String friendId) {
    	String hql = "from Friend where (userId = '" + userId + "' and status = 'A') or (friendId = '" + userId + "' and status = 'A')";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        
         Friend list = (Friend)query.list();
         
          return null;
          
		
	}
    @Override
	public Friend get(int id) {
    	Log.debug("**********Starting of get() method.");
		String hql = "from Friend where id = '" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Friend> list = query.list();
		
		if (list != null && !list.isEmpty()) {
			Log.debug("**********End of get() method.");
			return list.get(0);
		}
		else {
			return null;
		}
    }



	@Transactional
	public List<Friend> getNewFriendRequests(String userId) {
		 String hql = "from Friend where (friendId = '" + userId + "' and status = 'N') or (userId = '" + userId + "' and status = 'N')";
	        Query query = sessionFactory.getCurrentSession().createQuery(hql);
	        
	         List<Friend> list = (List<Friend>)query.list();
	         
	          return list;
		
	}

	
	}

