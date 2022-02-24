package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Post;

public interface IServicePost {
	Post findOne(Long idPost);

	List<Post> findAll();

	// Set<Post> findRecent();

	// Set<Post> findAllByOrderByCreationDateDesc();

	// Set<Post> findByUser(User user);

	// Set<Post> findByTopic(Long idTopic);

	// Set<Post> findByTopic(Topic topic);

	public Post save(Post post, Long idUser);

	void delete(Long idPost);

	// void delete(Post post);

	// void save(String content,
	// String userName,
	// Long idTopic);
	Post updatePost(Post p);
}
