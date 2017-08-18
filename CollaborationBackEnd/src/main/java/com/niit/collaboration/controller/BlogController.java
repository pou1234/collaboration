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

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Users;



@RestController
public class BlogController {

	private static final Logger log = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	//display all blogs
	@GetMapping(value = "/blogs")
	public ResponseEntity<List<Blog>> listBlog() {
		log.debug("**********Starting of listBlog() method.");
		List<Blog> blog = blogDAO.list();
		if(blog.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		log.debug("**********End of listBlog() method.");
		return new ResponseEntity<List<Blog>>(blog, HttpStatus.OK);
	}
	
	//create all blogs
	@PostMapping(value = "/blog/")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
		log.debug("**********Starting of createBlog() method.");
		if(blogDAO.get(blog.getId()) == null) {
			blogDAO.save(blog);
			log.debug("**********End of createBlog() method.");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		blog.setErrorMessage("Blog already exist with id : " +blog.getId());
		log.error("Blog already exist with id : " +blog.getId());
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}

	@PutMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id, @RequestBody Blog blog) {
		log.debug("**********Starting of updateBlog() method.");
		if(blogDAO.get(id) == null) {
			blog = new Blog();
			blog.setErrorMessage("Blog does not exist with id : " +blog.getId());
			log.error("Blog does not exist with id : " +blog.getId());
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		
		blogDAO.update(blog);
		log.debug("**********End of updateBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PutMapping(value = "/approveBlog/{id}")
	public ResponseEntity<Blog> approveBlog(@PathVariable("id") int id, @RequestBody Blog blog) {
		log.debug("**********Starting of updateBlog() method.");
		if(blogDAO.get(id) == null) {
			blog = new Blog();
			blog.setErrorMessage("Blog does not exist with id : " +blog.getId());
			log.error("Blog does not exist with id : " +blog.getId());
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		blog.setStatus("A");
		blogDAO.update(blog);
		log.debug("**********End of updateBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	@GetMapping(value = "/blog/")
	public ResponseEntity<Blog> showBlog(@RequestBody Blog blog) {
		log.debug("**********Starting of showBlog() method.");
		if(blogDAO.get(blog.getId()) == null) {
			blogDAO.save(blog);
			log.debug("**********End of showBlog() method.");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		blog.setErrorMessage("Blog already exist with id : " +blog.getId());
		log.error("Blog already exist with id : " +blog.getId());
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}

	@GetMapping(value = "/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id) {
		log.debug("**********Starting of getBlog() method.");
		Blog blog = (Blog)blogDAO.get(id);
		if(blog == null) {
			blog = new Blog();
			blog.setErrorMessage("Blog does not exist with id : " + id);
			log.error("Blog does not exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		log.debug("**********End of getBlog() method.");
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
}
	
	
	