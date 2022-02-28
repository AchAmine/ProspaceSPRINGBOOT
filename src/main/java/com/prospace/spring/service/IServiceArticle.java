package com.prospace.spring.service;

import java.util.HashMap;
import java.util.List;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.User;

public interface IServiceArticle {

	Article addArticle(Article A,Long idUser);
	void deleteArticle(Long id);
	Article updateArticle(Article A);
	List<Article> retrieveAllArticles();
	Article retrieveArticle(Long id);
	List<Article> getArticlesByUser(Long idUser);
	
	Article EnableComments(Long idArticle,Boolean action);
	
	HashMap<Integer, Integer> SortByReaction(Long idUser);
	HashMap<Integer, Integer> SortByComments(Long idUser);
}
