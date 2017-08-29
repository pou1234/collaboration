package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.catalina.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.daoimpl.BlogDAOImpl;
import com.niit.collaboration.daoimpl.UserDAOImpl;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Users;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {

	
	@Bean(name= "dataSource")
	public DataSource getOracleDataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    
	    dataSource.setUsername("COLL1");
	    dataSource.setPassword("COLL1");
	    
	    
	    Properties connectionProperties = new Properties();
	    connectionProperties.setProperty("hibernate.hbm2ddl.auto","update");
	    connectionProperties.setProperty("hibernate.show_sql","true");
	    connectionProperties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
	    connectionProperties.setProperty("hibernate.format_sql","true");
	    connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys","true");
	   
	    dataSource.setConnectionProperties(connectionProperties);
	    return dataSource;
	    
	}


       

       

       
       
       
   
       @Autowired
       @Bean(name = "sessionFactory")
       public SessionFactory getSessionFactory(DataSource dataSource){
    	  LocalSessionFactoryBuilder sessionBuilder = new  LocalSessionFactoryBuilder(dataSource);
    	 
    	  sessionBuilder.addAnnotatedClass(Users.class);
    	  sessionBuilder.addAnnotatedClass(Blog.class);
    	  sessionBuilder.addAnnotatedClass(Chat.class);
    	  sessionBuilder.addAnnotatedClass(Event.class);
    	  sessionBuilder.addAnnotatedClass(Friend.class);
    	 
    	
    	  
    	  return sessionBuilder.buildSessionFactory();
       }

       @Autowired
       @Bean(name="transactionManager")
       public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
    	   HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
    	   
    	   return transactionManager;
    	   
       }

       @Autowired
   	@Bean(name = "blogDAO")
   	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {
   		return new BlogDAOImpl(sessionFactory);
   	}

       @Autowired
      	@Bean(name = "blog")
      	public Blog getBlog(SessionFactory sessionFactory) {
      		return new Blog();
      	}

       
       }














