package com.niit.collaboration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	private static final Logger log=LoggerFactory.getLogger(AppInitializer.class);
	
	@Override
	protected Class[] getRootConfigClasses() {
		log.debug("Starting of the method getRootConfigClasses ");
		return new Class[]{AppConfig.class,WebSocketConfig.class};
	}

	@Override
	protected Class[] getServletConfigClasses() {
		log.debug("Starting of the method getServletConfigClasses ");
		return new Class[]{AppConfig.class,WebSocketConfig.class};
		
	}

	@Override
	protected String[] getServletMappings() {
		log.debug("Starting of the method getServletMappings ");
		return new String[] {"/"};
	}
}
