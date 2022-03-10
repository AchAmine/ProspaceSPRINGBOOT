package com.prospace.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
public class ChatNotification {

	private String id;
    private String senderId;
    private String senderName;
}
