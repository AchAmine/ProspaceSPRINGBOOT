package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Reaction;
import com.prospace.spring.entity.ReactionType;
import com.prospace.spring.entity.User;

public interface IServiceReaction {

	Reaction addReaction(Long userId , Long articleId , Reaction react);
	void deleteReaction(Long id);
	Reaction updateReaction(Reaction react);
	
	List<Reaction> retrieveArticleReactions(Long articleId);
	List<User> retrieveArticleReactors(Long articleId);
	List<User> retrieveArticleReactorsByType(Long articleId,ReactionType reactionType);
}
