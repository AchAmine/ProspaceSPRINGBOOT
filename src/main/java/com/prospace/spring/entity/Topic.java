package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

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
public class Topic implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTopic;

	@NonNull
	@Size(min = 1, max = 50, message = "{topic's title cannot be empty}")
	private String title;

	@NonNull
	@Size(min = 1,max = 300)
	private String description;

	private int views;

	private boolean closed;

	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@JsonIgnore
	@ManyToOne
	private Section section;
	@ManyToOne
	private User user;
	//@OneToMany
	//private Set<Post_Reaction> postReactions;

//	@OneToMany(cascade = CascadeType.ALL)
//	private Set<Post> posts;


}
