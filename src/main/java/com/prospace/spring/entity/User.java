package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

@NoArgsConstructor 
@AllArgsConstructor
@RequiredArgsConstructor 
@ToString
public class User implements Serializable{
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdUser() {
		return idUser;
	}

	public String getName() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public boolean isLocked() {
		return locked;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public Image getImage() {
		return image;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Set<Skill> getSkills() {
		return Skills;
	}

	public Set<Formation> getFormations() {
		return formations;
	}

	public Set<Formation> getFormations_organized() {
		return formations_organized;
	}

	public Set<Article> getArticles() {
		return Articles;
	}

	public Set<Offer> getOffers() {
		return Offers;
	}

	public Set<Quizz> getQuizz() {
		return Quizz;
	}

	public Set<Post> getPosts() {
		return Posts;
	}

	public Set<Topic> getTopics() {
		return Topics;
	}

	public Set<Event> getEventsParticipation() {
		return EventsParticipation;
	}

	public Set<Event> getEventsOrganized() {
		return EventsOrganized;
	}

	public Set<Tournament> getTournaments() {
		return Tournaments;
	}

	public Set<Complaint> getComplaints() {
		return Complaints;
	}

	public Badge getBadge() {
		return badge;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setSkills(Set<Skill> skills) {
		Skills = skills;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}

	public void setFormations_organized(Set<Formation> formations_organized) {
		this.formations_organized = formations_organized;
	}

	public void setArticles(Set<Article> articles) {
		Articles = articles;
	}

	public void setOffers(Set<Offer> offers) {
		Offers = offers;
	}

	public void setQuizz(Set<Quizz> quizz) {
		Quizz = quizz;
	}

	public void setPosts(Set<Post> posts) {
		Posts = posts;
	}

	public void setTopics(Set<Topic> topics) {
		Topics = topics;
	}

	public void setEventsParticipation(Set<Event> eventsParticipation) {
		EventsParticipation = eventsParticipation;
	}

	public void setEventsOrganized(Set<Event> eventsOrganized) {
		EventsOrganized = eventsOrganized;
	}

	public void setTournaments(Set<Tournament> tournaments) {
		Tournaments = tournaments;
	}

	public void setComplaints(Set<Complaint> complaints) {
		Complaints = complaints;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

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
	
	// --------------------------------------- End News -------------------------------------
	
	// --------------------------------------- Begin Partner -------------------------------------
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Offer> Offers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Quizz> Quizz;
	
	
	// --------------------------------------- End Partner -------------------------------------
	
	// --------------------------------------- Begin Forum -------------------------------------
	
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
	private Set<Event> EventsOrganized=new HashSet();
	
	@ManyToMany
	private Set<Tournament> Tournaments;
	
	// --------------------------------------- End Events -------------------------------------
	
	
	// --------------------------------------- Begin Complaint -------------------------------------
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Complaint> Complaints;
	
	@OneToOne
	private Badge badge;

	
	

	}
	
	// --------------------------------------- End Complaint -------------------------------------

