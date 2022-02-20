package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@NonNull
	private String name;
	@NonNull 
	private String userName;
	@NonNull 
	private String email;
	@NonNull 
	private String password;
	@NonNull 
	@Enumerated(EnumType.STRING)
	UserRole userRole;
	
	private boolean locked;
	
	private boolean enabled;
	
	private boolean isDeleted;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;
	
	@OneToOne
	private Image image;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@ManyToMany
	private Set<Skill> Skills;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="Participants")
	private Set<Formation> formations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="organizer")
	private Set<Formation> formations_organized;
	
	// --------------------------------------- Begin News -------------------------------------
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Article> Articles;
		
	@ToString.Exclude
	@OneToMany
	private Set<Article_Comment> articleComments;
	
	@ToString.Exclude
	@OneToMany
	private Set<Reaction> reactions;
	
	
	// --------------------------------------- End News -------------------------------------
	
	// --------------------------------------- End Partner -------------------------------------
	
	@OneToMany
	private Set<Rating> ratings; // Rating sur les offres 
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Offer> Offers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Quizz> Quizz;
	
	
	// --------------------------------------- End Partner -------------------------------------
	
	// --------------------------------------- Begin Forum -------------------------------------
	
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Post> Posts;
		
	
	@ToString.Exclude
	@OneToMany
	private Set<Post_Comment> postComments;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Topic> Topics;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Post_Reaction> postReactions;
	// --------------------------------------- End Forum -------------------------------------
	
	// --------------------------------------- Begin Events -------------------------------------
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="Participants")
	private Set<Event> EventsParticipation;
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user_organizer")
	private Set<Event> EventsOrganized;
	@ToString.Exclude
	@OneToMany
	private Set<Event_Comment> eventComments;
	
	@ManyToMany
	private Set<Tournament> Tournaments;
	
	// --------------------------------------- End Events -------------------------------------
	
	
	// --------------------------------------- Begin Complaint -------------------------------------
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Complaint> Complaints;
	
	@OneToOne
	private Badge badge;
	
	// --------------------------------------- End Complaint -------------------------------------
}
