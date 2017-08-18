package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Users;

@Repository("userDAO")
public interface UserDAO {

	public List<Users> list();
	
	public Users get(String id,String password);
	
	public Users get(String id);
	
	//public void saveOrUpdate(UserDetails UserDetails);
	
	public boolean save(Users userDetailsDetails);
	
	public boolean update(Users userDetailsDetails);
	
	public boolean delete(Users userDetailsDetails);
	
	public Users authenticate(String id, String password);
	
	public void setOnline(String userID);
	
	public void setOffline(String userID);

	public List<Users> getAllUsers();

 
		
	}
	

