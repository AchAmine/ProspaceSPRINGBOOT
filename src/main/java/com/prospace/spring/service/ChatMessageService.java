package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.configuration.ResourceNotFoundException;
import com.prospace.spring.entity.ChatMessage;
import com.prospace.spring.entity.MessageStatus;
import com.prospace.spring.repository.ChatMessageRepository;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    } 

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
    	
    	log.info("--------------IN FINDCHATMESSAGES FUNCTION------------------------");
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);
        log.info("----------------CHAT ID ? ---------"+chatId);
        var messages = repository.sortByDate(chatId);
        log.info("----------------------- MESSAGES -------------------------"+messages.size());
        if (messages.isEmpty()) {
        	messages = new ArrayList<>();
        }
                //chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        /*
        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }
        	*/
        return messages;
    }

    public ChatMessage findById(String id) {
        return repository
                .findById(Long.valueOf(id))
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                }).orElseThrow(() ->
                new ResourceNotFoundException("can't find message (" + id + ")"));
    }
 
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
    	
    	repository.updateStatuses(senderId, recipientId, status);
    }
    
    public void updateStatus(Long id) {
    	ChatMessage message = repository.findById(id).orElse(null);
    	message.setStatus(MessageStatus.DELIVERED);
    	repository.save(message);
    }
}
