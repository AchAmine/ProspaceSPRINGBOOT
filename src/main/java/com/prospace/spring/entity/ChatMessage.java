package com.prospace.spring.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class ChatMessage {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	   private String chatId;
	   private String senderId;
	   private String recipientId;
	   private String content;
	   private Date timestamp;
	   private MessageStatus status;
	   
}
