package com.niit.collaboration.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.ChatDAO;
import com.niit.collaboration.model.Chat;


@Repository("chatDAO")
public class ChatDAOImpl implements ChatDAO{

	private static final Logger log= LoggerFactory.getLogger(UserDAOImpl.class);

   @Autowired(required= true)
   private SessionFactory sessionFactory;
   
public ChatDAOImpl(SessionFactory sessionFactory){
	try{
		this.sessionFactory= sessionFactory;
	}catch(Exception e){
		log.error("Unable to connect to db");
		e.printStackTrace();
	}
}

@Transactional
public List<Chat> list(){
	String hql = "from chat";
	Query query= sessionFactory.getCurrentSession().createQuery(hql);
	
	@SuppressWarnings("unchecked")
	List<Chat> list=(List<Chat>) query.list();
	
	return list;
}

@Transactional
public boolean saveOrUpdate(Chat chat){
	try{
		sessionFactory.getCurrentSession().saveOrUpdate(chat);
		return true;
	}catch(HibernateException e){
		e.printStackTrace();
		return false;
		
	}
}

@Transactional
public void delete(int id){
	Chat chat= new Chat();
	chat.setId(id);
	sessionFactory.getCurrentSession().delete(chat);
}

@Transactional
public Chat get(int id){
	String hql="from Chat where id=" + id;
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
	
	@SuppressWarnings("unchecked")
	List<Chat> list=(List<Chat>) query.list();
	
	if(list != null && !list.isEmpty()){
		return list.get(0);
	}
	return null;

	
}

}
