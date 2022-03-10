package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Reaction;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.postReactionType;
import com.prospace.spring.repository.PostRepository;
import com.prospace.spring.repository.Post_ReactionRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServicePost_Reaction implements IServicePost_reaction {
	@Autowired
	Post_ReactionRepository postReactionRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public Post_Reaction addPost_Reaction(Long userId, Long postId, Post_Reaction reaction) {
		Post post = postRepository.findById(postId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);

		reaction.setPost(post);
		reaction.setUser(user);
		return postReactionRepository.save(reaction);
	}

	@Override
	public void deletePostReaction(Long id) {
		Post_Reaction reaction = postReactionRepository.findById(id).orElse(null);
		postReactionRepository.delete(reaction);

	}

	@Override
	public Post_Reaction updatePostReaction(Post_Reaction reaction) {
		// TODO Auto-generated method stub
		return postReactionRepository.save(reaction);
	}

	@Override
	public List<Post_Reaction> retrievePostReactions(Long postId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Reaction> reactions = postReactionRepository.findByPost(post);

		return reactions;
	}

	@Override
	public List<User> retrievePostReactors(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Reaction> reactions = postReactionRepository.findByPost(post);
		List<User> users = new ArrayList<>();

		for (Post_Reaction react : reactions) {
			users.add(react.getUser());
		}
		return users;
	}

	@Override
	public List<User> retrievePostReactorsByType(Long postId, postReactionType reactionType) {
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Reaction> reactions = postReactionRepository.findByPostAndType(post, reactionType);

		List<User> users = new ArrayList<>();

		for (Post_Reaction react : reactions) {
			users.add(react.getUser());
		}
		return users;
	}
	
	/***********/
	@Override
	@Scheduled(cron = "*/60 * * * * *")
	public void nbrePostsParGenre() {
		log.info("--- Nombre des reactions de types like :" + postReactionRepository.nbreByGenre(postReactionType.Like));
		log.info("--- Nombre des reactions de type dislike : " + postReactionRepository.nbreByGenre(postReactionType.Dislike));
	}

}
