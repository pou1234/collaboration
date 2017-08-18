package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Friend;

@Repository("friendDAO")
public interface FriendDAO {

	
	
	
	
    public boolean save(Friend friend);
	
	public boolean update(Friend friend);
	
	public Friend getSelectedFriend(String userId,String friendId);
	
	public Friend get(int id);
	
	public void setIsOnline(String userId);
	
	public void setOffline(String userId);

	public void rejectFriend(String userId);

	public List<Friend> getMyFriends(String userId);

	public  boolean isFriend(String userId, String friendId);

	public List<Friend> getNewFriendRequests(String userId);
     

 
	

	
	}
     
	


	

 
		

	


