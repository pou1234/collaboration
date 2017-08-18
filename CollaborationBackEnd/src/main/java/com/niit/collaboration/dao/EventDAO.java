package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Event;

@Repository		//@Repository annotation is a specialization of the @Component annotation with similar use and functionality...
public interface EventDAO {

	/**
	 *  Declare all CRUD Operations...
	 */
	
		public boolean save(Event event);		
		public boolean update(Event event);		
		public boolean delete(Event event);		
		public Event get(int id);		
		public List<Event> list();
}



