package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String firstname;
	@NonNull
	private String lastName;
	
	@Column(unique=true)
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
	@NonNull 
	private boolean isDeleted;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private Date createdAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;
	
	@OneToOne
	private Image image;
	
	@Temporal(TemporalType.DATE)
	@Transient
	private Date birthDate;
	@NonNull
	private Integer age;
	
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
	
	// --------------------------------------- End News -------------------------------------
	
	// --------------------------------------- Begin Partner -------------------------------------
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Offer> Offers;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Quizz> Quizz;
	
	
	// --------------------------------------- End Partner -------------------------------------
	
	// --------------------------------------- Begin Forum -------------------------------------
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Post> Posts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Topic> Topics;
	
	
	// --------------------------------------- End Forum -------------------------------------
	
	// --------------------------------------- Begin Events -------------------------------------
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="Participants")
	private Set<Event> EventsParticipation;
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user_organizer")
	private Set<Event> EventsOrganized;
	
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
