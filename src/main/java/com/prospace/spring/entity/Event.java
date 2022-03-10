package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idEvent;
	

	private String title;
	
	
	private String description;

	@Temporal(TemporalType.DATE)
	private Date startsAt;
	
	@Temporal(TemporalType.DATE)
	private Date endsAt;
	@NonNull
	private String location;
	
	private int slot;
	
	private float price;
		
    private float depenses ;
    
    private float profit ;
    
	@OneToOne
	private Image image;
	
	@ManyToOne
	private User user_organizer;
	
	@ManyToMany
	private Set<User> Participants;

	@OneToMany
	private Set<Activity> activities;
	
	 @JsonIgnore
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="event")
		private Set<Vote> votes;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdEvent() {
		return idEvent;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartsAt() {
		return startsAt;
	}

	public Date getEndsAt() {
		return endsAt;
	}

	public String getLocation() {
		return location;
	}

	public int getSlot() {
		return slot;
	}

	public float getPrice() {
		return price;
	}

	public float getDepenses() {
		return depenses;
	}

	public float getProfit() {
		return profit;
	}

	public Image getImage() {
		return image;
	}

	public User getUser_organizer() {
		return user_organizer;
	}

	public Set<User> getParticipants() {
		return Participants;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

}
