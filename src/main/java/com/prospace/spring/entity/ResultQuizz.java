package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

@ToString
public class ResultQuizz implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idResultQuizz;
	
	private float score;
	
	private int correctAnswers;
	
	private int falseAnswers;
	
	private int nbreQuestions;
	
	@ManyToOne 
	private User user; 
	@JsonIgnore
	
	@ManyToOne  
	private Quizz quizz;
	
	/*private int totalQuestions = 0;
	private int correctQuestions = 0;
	
	
	public void addAnswer(Boolean isCorrect) {
		
		if (isCorrect) {
			correctQuestions++;
		}
}*/
	}
