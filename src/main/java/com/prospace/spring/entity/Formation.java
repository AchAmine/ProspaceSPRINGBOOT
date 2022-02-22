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
public class Formation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idFormation;
	
	@NonNull
	private String subject;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startsAt;

	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date endsAt;
	@NonNull 
	private boolean isDeleted;
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;
	
	@ManyToMany
	private Set<User> Participants;
	
	@ManyToOne
	private User organizer;
}
