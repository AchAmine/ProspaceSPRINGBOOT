package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@RequiredArgsConstructor 
@ToString
public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idEvent;
	
	@NonNull
	private String title;
	
	@NonNull
	private String description;
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date startsAt;
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date endsAt;
	@NonNull
	private String location;
	
	private int slot;
	
	private float price;
	
	@OneToOne
	private Image image;
	
	@OneToMany
	private Set<Event_Comment> eventComments;
	
	@ManyToOne
	private User user_organizer;
	
	@ManyToMany
	private Set<User> Participants;

	@OneToMany
	private Set<Activity> activities;
}
