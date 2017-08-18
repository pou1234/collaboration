package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.ChatForum;

@Repository("chatforumDAO")
public interface ChatForumDAO {

	public List<ChatForum> list();
	
}
