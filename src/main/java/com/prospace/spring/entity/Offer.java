package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private String description;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startsAt;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date endsAt;

	@NonNull
	@Enumerated(EnumType.STRING)
	private OfferState state;
	
	//@ManyToOne
	//private User handledBy;
	@JsonIgnore
	@ManyToOne
	private User partner;
	
	
}
