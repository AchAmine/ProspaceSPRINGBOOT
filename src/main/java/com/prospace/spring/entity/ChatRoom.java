package com.prospace.spring.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class ChatRoom {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
    private String chatId;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
    
}
