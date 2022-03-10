package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.ChatMessage;
import com.prospace.spring.entity.MessageStatus;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{

	long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
	List<ChatMessage> findByChatId(String chatId);
	
	// messages sorted by date 
	@Query(value = "Select c  FROM ChatMessage c WHERE c.chatId = :chatId Order By c.timestamp")	
	List<ChatMessage> sortByDate(String chatId);
	
	
	@Modifying
	@Query(value = "update ChatMessage c set c.status = :status where (c.senderId = :senderId AND c.recipientId = :recipientId)"
			+ "OR (c.senderId = :recipientId AND c.recipientId = :senderId)")
	int updateStatuses(@Param("senderId") String senderId, @Param("recipientId") String recipientId, @Param("status") MessageStatus status);
}
