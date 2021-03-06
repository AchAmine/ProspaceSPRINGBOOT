package com.prospace.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Article implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idArticle;
	
	@NonNull
	private String title;
	
	@Lob 
	@NonNull
	private String content;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	//@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
	private Date updatedAt;
	
	private int views ; 
	
	private boolean enableComments;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private ArticleType type;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	
	
	
}
