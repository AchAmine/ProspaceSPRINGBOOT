package com.prospace.spring.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ArticleRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
		User user = userRepository.findById(A.getIdArticle()).orElse(null);
		A.setUser(user);
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
	public List<Article> retrieveOrderedByDate() {
		return articleRepository.retrieveOrderedByDate();
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

	@Override
	public Article EnableComments(Long idArticle, Boolean action) {
		Article article = articleRepository.findById(idArticle).orElse(null);
		article.setEnableComments(action);
		
		return articleRepository.save(article);
		
	}

	@Override
	public HashMap<Long, Long> SortByReaction(Long idUser ) {
		HashMap<Long, Long> hMap = new HashMap<Long, Long>();
		List<Object[]> listStaff = articleRepository.SortByReaction(idUser);
		for (Object[] obj : listStaff) {
			log.info("------- ADMIN --------------"+(Long)obj[0]);
			log.info("------- ARTICLE --------------"+(Long)obj[1]);
			if (hMap.containsKey((Long)obj[0])) {
				hMap.put( (Long)obj[0] , hMap.get((Long)obj[0])+1);
			} else {
			hMap.put( (Long)obj[0], 1L);
			}
		}
		return hMap;
	}

	@Override
	public HashMap<Long, Long> SortByComments(Long idUser) {
		HashMap<Long, Long> hMap = new HashMap<Long, Long>();
		List<Object[]> listStaff = articleRepository.SortByComments(idUser);
		for (Object[] obj : listStaff) {
			log.info("------- ADMIN --------------"+(Long)obj[0]);
			log.info("------- ARTICLE --------------"+(Long)obj[1]);
			if (hMap.containsKey((Long)obj[0])) {
				hMap.put( (Long)obj[0] , hMap.get((Long)obj[0])+1);
			} else {
			hMap.put( (Long)obj[0], 1L);
			}
		}
		return hMap;
	}

	@Override
	public HashMap<Long, Long> userPreferences(Long idUser) {
		
		HashMap<Long, Long> commenthMap = SortByComments(idUser);
		HashMap<Long, Long> reactionhMap = SortByReaction(idUser );
	
		log.info("--------- commentmap size"+commenthMap.size());
		log.info("--------- reactionmap size"+reactionhMap.size());
		if (commenthMap.size() > reactionhMap.size()) {
			log.info("--------- MERGING INTO COMMENTHMAP");
			reactionhMap.forEach(
					  (key, value) -> commenthMap.merge(key, value, (v1, v2) -> v1 + v2));
			log.info("MAP ------- 1"+commenthMap);
			return commenthMap;
		} else {
			log.info("--------- MERGING INTO REACTIONHMAP");
			commenthMap.forEach(
					  (key, value) -> reactionhMap.merge(key, value, (v1, v2) -> v1 + v2));
			log.info("MAP ------ 2"+reactionhMap);
			return reactionhMap;
		}
		
		
	}

	@Override
	public List<Article> FollowingArticles(Long idUser) {
		User u = userRepository.findById(idUser).orElse(null);
		List<User> followers = new ArrayList<User>();
		
		for (User user : u.getFollowers()) {
			followers.add(user);
		}
		return articleRepository.FollowingArticles(followers);
	}

	

	
}
