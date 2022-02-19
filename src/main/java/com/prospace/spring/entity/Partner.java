package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Partner implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idPartner;
	
	@NonNull
	private String name;

	@NonNull
	private String email;
	
	@NonNull
	private String password;
	
	@NonNull
	private String description;
	
	@OneToOne
	private Image image;
	
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date startsAt;
	
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date endsAt;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Offer> Offers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partner")
	private Set<Quizz> Quizz;
	
	@ManyToOne
	private User addedBy;
	
	@ManyToOne
	private User deletedBy;
	
}
