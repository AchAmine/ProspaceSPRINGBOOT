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
public class Post implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idPost;
	
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@OneToMany
	private Set<Post_Reaction> postReactions;
	
	@OneToMany
	private Set<Post_Comment> postComments;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private Set<Topic> Topics;
}
