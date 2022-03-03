package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor

@ToString
public class Event_Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idComment;
	
	
	private String content;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date postedAt;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Event event;
	
	

}
