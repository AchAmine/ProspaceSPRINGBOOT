package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PostRepository;
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

	/*
	 * @Override public Set<Post> findRecent() { // TODO Auto-generated method stub
	 * return postRepository.findTop5ByOrderByCreationDateDesc(); }
	 * 
	 * @Override public Set<Post> findAllByOrderByCreationDateDesc() { // TODO
	 * Auto-generated method stub return
	 * postRepository.findAllByOrderByCreationDateDesc(); }
	 */

	
	  @Override
	  public List<Post> findByUser(User user) { // TODO Auto-generated
	   return postRepository.findByUser(user); 
	   }
	 

	
	/*  @Override public Set<Post> findByTopic(Long idTopic) { return
			  findByTopic(topicService.RetrieveTopic(idTopic)); }
	  
	  @Override public Set<Post> findByTopic(Topic Topics) { return
	  postRepository.findByTopic(Topics); }
	 */
	@Override
	public Post save(Post post, Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		post.setUser(user);
		return postRepository.save(post);
	}

	@Override
	public void delete(Long idPost) {
		// TODO Auto-generated method stub
		postRepository.deleteById(idPost);

		// delete(findOne(idPost));
	}

	@Override
	public Post updatePost(Post p) {
		// TODO Auto-generated method stub
		return postRepository.save(p);
	}

	@Override
	public List<Post> findRecent() {
		// TODO Auto-generated method stub
		return postRepository.findTop5ByOrderByCreatedAtDesc();
	}

	@Override
	public List<Post> retrieveByDateSql() {
		// TODO Auto-generated method stub
	return (List<Post>)postRepository.retrieveByDateSql();
		} 
}
