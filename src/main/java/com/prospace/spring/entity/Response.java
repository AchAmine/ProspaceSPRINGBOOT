package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
 
@ToString
public class Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idResponse;
	
	//@ManyToOne
	//private Question question; //averifier
	@ManyToMany
	private List<Answer> selectedAnswers;
	
	@ManyToOne
	private Quizz quizz;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User userresponse;

}
