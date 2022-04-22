package com.prospace.spring.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.User;

public interface IServiceArticle {

	Article addArticle(Article A,Long idUser,MultipartFile file);
	void deleteArticle(Long id);
	Article updateArticle(Article A,MultipartFile file);
	List<Article> retrieveAllArticles();
	List<Article> retrieveOrderedByDate();
	Article retrieveArticle(Long id);
	List<Article> getArticlesByUser(Long idUser);
	
	Article EnableComments(Long idArticle,Boolean action);
	
	HashMap<Long, Long> SortByReaction(Long idUser);
	HashMap<Long, Long> SortByComments(Long idUser);
	HashMap<Long, Long> userPreferences(Long idUser);
	
	List<Article> FollowingArticles(Long idUser); 
}
