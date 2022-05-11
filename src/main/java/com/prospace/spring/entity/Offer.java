package com.prospace.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
public class Offer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idOffer;
	
	@NonNull
	private String title; 
	@NonNull
	private String Type; 
	
	@NonNull
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date startsAt;
	
	@Temporal(TemporalType.DATE)
	private Date endsAt;

	@Enumerated(EnumType.STRING)
	private OfferState state;
	
	private float moyRatings;
	
	
	//@ManyToOne
	//private User handledBy;
	@ManyToOne
	private User partner;
	
	@Transient
	private String PartnerEmail;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

	@OneToMany(mappedBy="offer")
	private List<Rating> Ratings;
	
	

	
}
