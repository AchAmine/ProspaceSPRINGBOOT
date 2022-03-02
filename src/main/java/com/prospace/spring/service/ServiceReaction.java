package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Reaction;
import com.prospace.spring.entity.ReactionType;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ArticleRepository;
import com.prospace.spring.repository.ReactionRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceReaction implements IServiceReaction{
	
	@Autowired
	ReactionRepository reactionRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Reaction addReaction(Long userId, Long articleId, Reaction react) {
		Article article = articleRepository.findById(articleId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		
		react.setArticle(article);
		react.setUser(user);
		return reactionRepository.save(react);
	}

	@Override
	public void deleteReaction(Long id) {
		Reaction react = reactionRepository.findById(id).orElse(null);
		reactionRepository.delete(react);	
	}

	@Override
	public Reaction updateReaction(Reaction react) {
		Reaction r = reactionRepository.findById(react.getIdReaction()).orElse(null);
		react.setUser(r.getUser());
		react.setArticle(r.getArticle());
		
		return reactionRepository.save(react);
	}

	@Override
	public List<Reaction> retrieveArticleReactions(Long articleId) {
		Article article = articleRepository.findById(articleId).orElse(null);
		List<Reaction> reactions =  reactionRepository.findByArticle(article);
		
		return reactions;
	}

	// Avec List
	@Override
	public List<User> retrieveArticleReactors(Long articleId) {
		Article article = articleRepository.findById(articleId).orElse(null);
		List<Reaction> reactions =  reactionRepository.findByArticle(article);
		List<User> users = new ArrayList<>();
		
		for(Reaction react : reactions ) {
			users.add(react.getUser());
		}	
		return users;
	}
	
	// avec HashMap -- 
	@Override
	public HashMap<User, ReactionType> retrieveUsersReactions(Long articleId) {
		Article article = articleRepository.findById(articleId).orElse(null);
		List<Reaction> reactions =  reactionRepository.findByArticle(article);
		HashMap<User,ReactionType> user_reaction = new HashMap<User,ReactionType>();
		for(Reaction react : reactions ) {
			user_reaction.put(react.getUser(), react.getType());
		}
		return user_reaction;
	}

	@Override
	public List<User> retrieveArticleReactorsByType(Long articleId, ReactionType reactionType) {
		Article article = articleRepository.findById(articleId).orElse(null);
		List<Reaction> reactions =  reactionRepository.findByArticleAndType(article,reactionType);
		
		List<User> users = new ArrayList<>();
		
		for(Reaction react : reactions ) {
			users.add(react.getUser());
		}
		return users;
	}

	

}
