package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PostRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServicePost implements IServicePost {

	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;

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

	/*
	 * @Override public Set<Post> findByUser(User user) { // TODO Auto-generated
	 * method stub return postRepository.findByUser(user); }
	 */

	/*
	 * @Override public Set<Post> findByTopic(int idTopic) { return
	 * findByTopic(topicService.findOne(idTopic)); }
	 * 
	 * @Override public Set<Post> findByTopic(Topic topic) { return
	 * postRepository.findByTopic(topic); }
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

	/*
	 * @Override public void delete(Post post) { // TODO Auto-generated method stub
	 * postRepository.delete(post); }
	 */
	/*
	 * @Override public void save(String content, String userName, Long idTopic) {
	 * // TODO Auto-generated method stub Post post = new Post();
	 * post.setTopic(topicService.findOne(idTopic));
	 * post.setUser(userService.findByUsername(username)); post.setContent(content);
	 * save(post); }
	 */
}
