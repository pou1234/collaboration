package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Chat;

@Repository("chatDAO")
public interface ChatDAO {

	public List<Chat> list();
	
	public boolean saveOrUpdate(Chat chat);
	
	public void delete(int id);
	
}
	
