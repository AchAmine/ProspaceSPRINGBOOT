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
public class Complaint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idComplaint;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private typeComplaint type;
	
	@OneToMany
	private Set<Image> Images;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private stateComplaint state;
	
	@ManyToOne
	private User user;
}
