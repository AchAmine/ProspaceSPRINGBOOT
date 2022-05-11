package com.prospace.spring.controllers;



import lombok.var;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.ChatMessage;

@RestController
@Slf4j
@CrossOrigin
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private com.prospace.spring.service.ChatMessageService chatMessageService;
    @Autowired private com.prospace.spring.service.ChatRoomService chatRoomService;

	/*
	 * @MessageMapping("/{username}/msgs") public void processMessages(@Payload
	 * com.prospace.spring.entity.ChatMessage chatMessage,@DestinationVariable
	 * String username) {
	 * 
	 * log.info("----------------in chat ");
	 * log.info("-------------------chat message : "+chatMessage);
	 * log.info("-------------USERNAME------------------"+username); var chatId =
	 * chatRoomService .getChatId(chatMessage.getSenderId(),
	 * chatMessage.getRecipientId(), true); chatMessage.setChatId(chatId);
	 * log.info("CHAT ID IN CONTROLLER"+chatId); // HISTORIQUE CONVERSATION
	 * List<ChatMessage> chatmsgs =
	 * chatMessageService.findChatMessages(chatMessage.getSenderId(),
	 * chatMessage.getRecipientId());
	 * 
	 * for (int i = 0 ; i<chatmsgs.size() ; i++) {
	 * log.info("size : "+chatmsgs.size()); ChatMessage ch = chatmsgs.get(i);
	 * messagingTemplate.convertAndSendToUser( username,"/queue/messages", ch);
	 * log.info("in loop : "+i); }
	 * 
	 * }
	 */
    
    @MessageMapping("/chat")
    public void processMessage(@Payload com.prospace.spring.entity.ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId);
        com.prospace.spring.entity.ChatMessage saved = chatMessageService.save(chatMessage);
        log.info("Chat ID => "+chatId);
        log.info("message to send : "+saved.getContent());
        messagingTemplate.convertAndSendToUser(
                chatId,"/queue/message",
                saved);
        
        
		/*
		 * messagingTemplate.convertAndSendToUser(
		 * chatMessage.getRecipientId(),"/queue/message", saved);
		 * messagingTemplate.convertAndSendToUser(
		 * chatMessage.getSenderId(),"/queue/message", saved);
		 */
        	
    }
    @GetMapping("/chatId/{username}/{recipientId}")
    public String getChatId(
            @PathVariable("username") String username,
            @PathVariable("recipientId") String recipientId) {
    	log.info("username"+username);
    	log.info("recipientId"+recipientId);
    	if (username.equalsIgnoreCase("undefined") || recipientId.equalsIgnoreCase("undefined")) {
    		return null;
    	}
    	var chatId = chatRoomService
                .getChatId(username, recipientId, true);
    	log.info("---------get Chat ID--------------"+chatId);
        return chatRoomService
                .getChatId(username, recipientId, true);
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
}
