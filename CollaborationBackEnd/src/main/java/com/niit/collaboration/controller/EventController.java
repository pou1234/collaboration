package com.niit.collaboration.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;


@RestController
public class EventController {

	private static final Logger log = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	Event event;
	
	@Autowired
	EventDAO eventDAO;
	
	//diaplay all events
	@GetMapping(value = "/event")
	public ResponseEntity<List<Event>> listEvent() {
		log.debug("**********Starting of listEvent() method.");
		List<Event> event = eventDAO.list();
		if(event.isEmpty()) {
			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listEvent() method.");
		return new ResponseEntity<List<Event>>(event, HttpStatus.OK);
	}
	
	@PostMapping(value = "/event/")
	public ResponseEntity<Event> createEvent(@RequestBody Event event) {
		log.debug("**********Starting of createEvent() method.");
		if(eventDAO.get(event.getId()) == null) {
			eventDAO.save(event);
			log.debug("**********End of createEvent() method.");
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		}
		event.setErrorMessage("Event already exist with id : " +event.getId());
		log.error("Event already exist with id : " +event.getId());
		return new ResponseEntity<Event>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/event/{id}")
	public ResponseEntity<Event> getEvent(@PathVariable("id") int id) {
		log.debug("**********Starting of getEvent() method.");
		Event event = eventDAO.get(id);
		if(event == null) {
			event = new Event();
			event.setErrorMessage("No event exist with id : " + id);
			log.error("No event exist with id : " + id);
			return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getEvent() method.");
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

}
