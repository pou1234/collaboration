package com.niit.collaboration.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.niit.collaboration.model.Message;

@Controller
public class ChatController  {
    private static final Logger log= LoggerFactory.getLogger(ChatController.class);
    
@Autowired
private SimpMessagingTemplate simpMessagingTemplate;
    
@Autowired
HttpSession session;
    
@MessageMapping("/chat")
@SendTo("/queue/message/{userID}")
public void sendMessage(Message message,@DestinationVariable("userID")String userID){
	log.debug("Calling the method sendMessage");
	log.debug("Message : ",message.getMessage());
	log.debug("Message ID :", message.getId());
	
	message.setUserID((String)session.getAttribute("loggedInUserID"));
	
	simpMessagingTemplate.convertAndSend("/user/" + userID, message);
	
}
    
    
    
    
    
    
    
    
    
}
