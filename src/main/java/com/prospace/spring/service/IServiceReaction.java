package com.prospace.spring.service;

import com.prospace.spring.entity.Reaction;

public interface IServiceReaction {

	Reaction addReaction(Long userId , Long articleId , Reaction react);
	void deleteReaction(Long id);
	Reaction updateReaction(Reaction react);
}
