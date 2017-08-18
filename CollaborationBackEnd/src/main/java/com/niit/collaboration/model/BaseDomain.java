package com.niit.collaboration.model;

import java.io.Serializable;
//import javax.persistence.Entity;
//import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component

public class BaseDomain implements Serializable {
	
	private static final long serialVersionUID = 10L;
	
    private String errorCode;
	
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



   
}
