package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;

import java.util.List;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String firstName;
	@NonNull
	private String lastName;
	
	@Column(unique=true)
	@NonNull 
	private String userName;
	@NonNull 
	private String email;
	@NonNull 
	private String password;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> userRoles = new HashSet<>();
	
	private boolean locked;
	
	private boolean enabled;
	@NonNull 
	private boolean isDeleted;
	/*@NonNull
	@Column(name = "failed_attempt")
    private int failedAttempt;*/
	
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
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@NonNull
	private Integer age;
	@Column(name = "resettoken")
	private String resettoken;
	
		public boolean isEnabled() {
			return enabled;
		}
		
		public boolean isLocked() {
			return locked;
		}
		
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Skill> Skills;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy="Participants",fetch = FetchType.EAGER)
	private Set<Formation> formations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="organizer",fetch = FetchType.EAGER)
	private Set<Formation> formations_organized;

	
	// --------------------------------------- Begin News -------------------------------------
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user",fetch = FetchType.EAGER)
	private Set<Article> Articles;
	
	
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER)
	private Set<User> followers;
	
	
	
	// --------------------------------------- End News -------------------------------------
	
	// --------------------------------------- Begin Partner -------------------------------------
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner",fetch = FetchType.EAGER)
	private Set<Offer> Offers;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner",fetch = FetchType.EAGER)
	private Set<Quizz> Quizz;
	@JsonIgnore
	@OneToMany(mappedBy="user",fetch = FetchType.EAGER)
	private Set<ResultQuizz> resultQuizz;
	
	@JsonIgnore
	@OneToOne(mappedBy="userresponse",fetch = FetchType.EAGER)
	private Response Response;

	
	// --------------------------------------- End Partner -------------------------------------
	

	// --------------------------------------- Begin Forum
		// -------------------------------------
		
		@JsonIgnore
		@ToString.Exclude
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
		private Set<Post_Reaction> post_Reaction;
		
		/*********************************************************/
		@JsonIgnore
		@ToString.Exclude
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.EAGER)
		private Set<Post> Posts;

		/***************************************** begin section */
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
		private Set<Section> Sections;
		/**** end section */
		/***************************************** begin topic */
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.EAGER)
		private Set<Topic> Topics;
		/**** end topic */
		// --------------------------------------- End Forum
		// -------------------------------------


	
	// --------------------------------------- Begin Events -------------------------------------
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="Participants",fetch = FetchType.EAGER)
	private Set<Event> EventsParticipation;
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user_organizer",fetch = FetchType.EAGER)
	private Set<Event> EventsOrganized;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Tournament> Tournaments;
	
	// --------------------------------------- End Events -------------------------------------
	
	
	// --------------------------------------- Begin Complaint -------------------------------------
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user",fetch = FetchType.EAGER)
	private Set<Complaint> Complaints;
	
	@OneToOne
	private Badge badge;
	
	// --------------------------------------- End Complaint -------------------------------------
}