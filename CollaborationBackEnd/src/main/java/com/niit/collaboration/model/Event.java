package com.niit.collaboration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Event extends BaseDomain implements Serializable {
	

	private static final long serialVersionUID = 10L;

	/**
	 * declare the database column names for User... 
	 */
	
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_AUTO_EVENT_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	private int id;
	
	private String name;
	
	private String venue;
	
	private String description;
	
	@Column(name = "DATEON")
	private Date dateon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateon() {
		return dateon;
	}

	public void setDateon(Date dateon) {
		this.dateon = dateon;
	}

	
}

