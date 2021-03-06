package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
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
	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
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
		return postRepository.save(post);
	}

	@Override
	public void delete(Long idPost) {
		// TODO Auto-generated method stub
		postRepository.deleteById(idPost);
	}

	@Override
	public Post updatePost(Post p) {
		// TODO Auto-generated method stub
		String str = post_commentService.GetCensoredText(p.getContent());
		String str1 = post_commentService.GetCensoredText(p.getTitle());
		p.setContent(str);
		p.setTitle(str1);
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

}
