package com.niit.collaboration.model;

	import java.io.Serializable;

	import javax.persistence.Entity;
	import javax.persistence.Id;
	import javax.persistence.Table;
	
	import org.springframework.stereotype.Component;

	@Entity
	@Table
	@Component
	public class Users extends BaseDomain implements Serializable {

		
		/**
		 * 
		 */
		private static final long serialVersionUID = 10L;
		

		
		@Id
		
		private String id;
		
		private String name;
		
        private String password;
		
		private String emailId;
		
		private String role;
		
		private String isOnline;
		
		

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getIsOnline() {
			return isOnline;
		}

		public void setIsOnline(String isOnline) {
			this.isOnline = isOnline;
		}

		
		

		
	}




