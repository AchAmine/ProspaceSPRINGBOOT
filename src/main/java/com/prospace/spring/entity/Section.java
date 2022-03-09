package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Section implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSection;

	@NonNull
	@Size(min = 1, max = 50, message = "{Section Name cannot be empty}")
	private String name;

	@NonNull
	@Size(min = 1,max = 300)
	private String description;
	@ManyToOne
	private User user;
	@OneToMany
	private Set<Topic> topics;
	@OneToMany
	private Set<Post_Reaction> postReactions;


}
