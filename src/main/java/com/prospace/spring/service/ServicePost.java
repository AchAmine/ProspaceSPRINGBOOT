package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Comment;
import com.prospace.spring.entity.Section;
import com.prospace.spring.entity.Topic;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PostRepository;
import com.prospace.spring.repository.Post_ReactionRepository;
import com.prospace.spring.repository.TopicRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServicePost implements IServicePost {

	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	IServiceTopic topicService;
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	IServicePost_Comment post_commentService;
	@Autowired
	Post_ReactionRepository post_reactionRepository;

	@Override
	public Post findOne(Long idPost) {
		// TODO Auto-generated method stub
		return postRepository.findById(idPost).get();
	}

	@Override
	public List<Post> findAll(Long topicId) {
		// TODO Auto-generated method stub
		//return postRepository.findAll();
		Topic topic = topicRepository.findById(topicId).orElse(null);

		List<Post> posts =  postRepository.findByTopic(topic);
return posts;
	}

	@Override
	public List<Post> findByUser(User user) { // TODO Auto-generated
		return postRepository.findByUser(user);
	}

	@Override
	public Post save(Post post, Long idUser,Long topicId) {
		User user = userRepository.findById(idUser).orElse(null);
		Topic topic = topicRepository.findById(topicId).orElse(null);
		post.setTopic(topic);
		post.setUser(user);
		String str = post_commentService.GetCensoredText(post.getContent());
		String str1 = post_commentService.GetCensoredText(post.getTitle());
		post.setContent(str);
		post.setTitle(str1);
		Date date = new Date(System.currentTimeMillis());
		post.setCreatedAt(date);
		
		return postRepository.save(post);
	}

	@Override
	@Transactional
	public void delete(Long idPost) {
		// TODO Auto-generated method stub
		postRepository.deletePost(idPost);
	}

	@Override
	public Post updatePost(Post p) {
		Post post = postRepository.findById(p.getIdPost()).orElse(null);

		// TODO Auto-generated method stub
		String str = post_commentService.GetCensoredText(p.getContent());
		String str1 = post_commentService.GetCensoredText(p.getTitle());
		p.setContent(str);
		p.setTitle(str1);
		Date date = new Date(System.currentTimeMillis());
		p.setUpdatedAt(date);

		// TODO Auto-generated method stub
	p.setTopic(post.getTopic());
		p.setUser(post.getUser());
		return postRepository.save(p);
	}

	@Override
	public List<Post> findRecent() {
		// TODO Auto-generated method stub
		return postRepository.findTop5ByOrderByCreatedAtDesc();
	}

	public List<Post> findByPostReactions() {
		// TODO Auto-generated method stub
		List<Post> post = postRepository.findByPostReactions();
		return post;

	}
	
	////////////////////////////////////////////
	
}
