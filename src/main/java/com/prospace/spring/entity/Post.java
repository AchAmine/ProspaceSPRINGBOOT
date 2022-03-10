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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

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
public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPost;

	@NonNull
	@Size(min = 1, max = 50)
	private String title;
	@NonNull
	@Size(min = 1, max = 50, message = "{post's content cannot be empty}")
	private String content;
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@OneToMany
	private Set<Post_Reaction> postReactions;

	// @OneToMany
	// private Set<Post_Comment> postComments;

	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@ManyToOne
	private Topic topic;
}
