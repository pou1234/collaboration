package com.niit.collaboration.junit.test;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.daoimpl.*;
import com.niit.collaboration.model.*;

import org.junit.Test;

public class BlogJUnitTestCase {
	//Logger log =  Logger.getLogger(BlogJUnitTestCase.class);
	
	@Autowired
	BlogDAO blogDAO;	//instance of BlogDAO created...
	
	@Autowired
	Blog blog;		//instance of Blog created...
	
	AnnotationConfigApplicationContext context;	
	@Before
	public void init() {	//init is just a method to initialize the instances...
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();		
		
		blogDAO = (BlogDAO) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
		
		
	}

	@Test
		public void listBlog() {
			
			assertEquals(blogDAO.list().size(), 4);
		}
			
		//@Test
		public void addBlog() {
			
			blog.setTitle("Blog");
			blog.setStatus("N");
			blog.setContent("This is my seventh blog...");
			blog.setOwner("u100");
			
			assertEquals(blogDAO.save(blog), true);
		}
		
		
		
		//@Test
		public void deleteBlog() {
			blog.setId(1);
			
			assertEquals(blogDAO.delete(blog), true);
		}
		
		//@Test
		public void getBlog() {
			
			assertEquals(blogDAO.get(1).getTitle(), "Blog 100");
		}

	
	
	
	//@Test
	public void test() {
		fail("Not yet implemented");
	}

}
