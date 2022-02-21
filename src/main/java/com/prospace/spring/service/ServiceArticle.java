package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ArticleRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceArticle implements IServiceArticle{
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public Article addArticle(Article A,Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		A.setUser(user);
		user.getArticles().add(A);
	/*	Set<Article> listArticles = user.getArticles();
		listArticles.add(A);
		user.setArticles(listArticles); */
		return articleRepository.save(A);
	}

	@Override
	public void deleteArticle(Long id) {
		Article article = articleRepository.findById(id).orElse(null);
		articleRepository.delete(article);
		
	}

	@Override
	public Article updateArticle(Article A) {
		return articleRepository.save(A);
	}

	@Override
	public List<Article> retrieveAllArticles() {
		return articleRepository.findAll();
	}
	
	@Override
	public Article retrieveArticle(Long id) {
		return articleRepository.findById(id).orElse(null);
	}

	@Override
	public List<Article> getArticlesByUser(Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		List<Article> listArticles = articleRepository.findByUser(user);
		
		// 2 eme methode 
		/*
		List<Article> listArticles = new ArrayList<Article>();
		listArticles.addAll(user.getArticles());
		*/		
		return listArticles;
	}

	
}
