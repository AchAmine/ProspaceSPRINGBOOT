package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
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
public class Post_Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idComment;
	
	@NonNull
	private String content;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date postedAt;
	

	@ManyToOne
	private Post post;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@OneToMany
	private Set<Post_Comment> replies;
	@ManyToOne
	private Post_Comment pComment;
	@ManyToOne
	private User user;
	@OneToMany
	private Set<Post_Reaction> postReactions;


}
