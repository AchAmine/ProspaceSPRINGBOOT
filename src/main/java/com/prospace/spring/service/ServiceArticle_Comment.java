package com.prospace.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Article_Comment;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ArticleRepository;
import com.prospace.spring.repository.Article_CommentRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceArticle_Comment implements IServiceArticle_Comment{

	@Autowired
	Article_CommentRepository article_commentRepository ; 
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Article_Comment addComment(Long userId , Long articleId , Article_Comment comment) {
		Article article = articleRepository.findById(articleId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		comment.setUser(user);
		comment.setArticle(article);
		
		return article_commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		Article_Comment comment = article_commentRepository.findById(id).orElse(null);
		article_commentRepository.delete(comment);	
	}

	@Override
	public Article_Comment updateComment(Article_Comment comment) {
		Article_Comment artcom = article_commentRepository.findById(comment.getIdComment()).orElse(null);
		comment.setArticle(artcom.getArticle());
		comment.setUser(artcom.getUser());
		return article_commentRepository.save(comment);
	}

	@Override
	public List<Article_Comment> retrieveArticleComments(Long articleId) {
		Article article = articleRepository.findById(articleId).orElse(null);
		List<Article_Comment> comments =  article_commentRepository.findByArticle(article);
		
		return comments;
	}
	
	

}
