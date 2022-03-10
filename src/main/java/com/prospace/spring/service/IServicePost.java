package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.User;

public interface IServicePost {
	Post findOne(Long idPost);

	List<Post> findAll();

	List<Post> findRecent();

	List<Post> findByUser(User user);

	public Post save(Post post, Long idUser,Long topicId);

	void delete(Long idPost);

	Post updatePost(Post p);

	/***********************/
	List<Post> findByPostReactions();
	/*****************/

}
