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

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.daoimpl.UserDAOImpl;
import com.niit.collaboration.model.Blog;
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
	    
	    dataSource.setUsername("COLL");
	    dataSource.setPassword("COLL");
	    
	    
	    Properties connectionProperties = new Properties();
	    connectionProperties.setProperty("hibernate.hbm2ddl.auto","update");
	    connectionProperties.setProperty("hibernate.show_sql","true");
	    connectionProperties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
	    connectionProperties.setProperty("hibernate.format_sql","true");
	    connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys","true");
	    //connectionProperties.setProperty("hibernate.default_schema","COLB_DB");
	    dataSource.setConnectionProperties(connectionProperties);
	    return dataSource;
	    
	}


       /* @Bean(name = "h2DataSource")
         public DataSource getH2DataSource(){
        	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	    dataSource.setDriverClassName("org.h2.Driver");
    	    dataSource.setUrl("jdbc:h2:mem:tmp.db;INIT=CREATE SCHEMA IF NOT EXISTS NIITDB");
    	    
    	    dataSource.setUsername("sa");
    	    dataSource.setPassword("");
    	    
    	    
    	    Properties connectionProperties = new Properties();
    	    connectionProperties.setProperty("hibernate.hbm2ddl.auto","update");
    	    connectionProperties.setProperty("hibernate.show_sql","true");
    	    connectionProperties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
    	    dataSource.setConnectionProperties(connectionProperties);
    	    return dataSource;
        }*/

       /* @Bean(name = "dataSource")
        public DataSource getMySQLDataSource(){
        	BasicDataSource dataSource = new BasicDataSource();
        	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        	dataSource.setUrl("jdbc:mysql://localhost:3306/niitdb");
        	dataSource.setUsername("root");
        	dataSource.setPassword("root");
        	
        	return dataSource;
        	
        	}*/

       /*private Properties getHibernateProperties(){
    	   Properties properties= new Properties();
    	   properties.put("hibernate.show_sql","true");
    	   properties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
    	   properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
    	   return properties;
       }*/
       
       
   
       @Autowired
       @Bean(name = "sessionFactory")
       public SessionFactory getSessionFactory(DataSource dataSource){
    	  LocalSessionFactoryBuilder sessionBuilder = new  LocalSessionFactoryBuilder(dataSource);
    	  //sessionBuilder.addProperties(getHibernateProperties());
    	  sessionBuilder.addAnnotatedClass(Users.class);
    	  sessionBuilder.addAnnotatedClass(Blog.class);
    	 // sessionBuilder.addAnnotatedClass(Chat.class);
    	  sessionBuilder.addAnnotatedClass(Event.class);
    	  sessionBuilder.addAnnotatedClass(Friend.class);
    	 // sessionBuilder.addAnnotatedClass(Job.class);
    	//  sessionBuilder.addAnnotatedClass(JobApplication.class);
    	//  sessionBuilder.addAnnotatedClass(ChatForum.class);
    	//  sessionBuilder.addAnnotatedClass(ChatForumComment.class);
    	  
    	  return sessionBuilder.buildSessionFactory();
       }

       @Autowired
       @Bean(name="transactionManager")
       public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
    	   HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
    	   
    	   return transactionManager;
    	   
       }

       
       
       
       }














