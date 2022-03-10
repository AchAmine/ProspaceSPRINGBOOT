package com.prospace.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Post_Reaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReaction;

	@NonNull
	@Enumerated(EnumType.STRING)
	private postReactionType type;

	@ManyToOne
	private Post post;
	
	@ManyToOne
	private Post_Comment postComment;

	@ManyToOne
	private Section section;
	
	@ManyToOne
	private Topic topic;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post_Reaction post_Reaction;

}
