package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Comment;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PostRepository;
import com.prospace.spring.repository.Post_CommentRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServicePost_Comment implements IServicePost_Comment {
	@Autowired
	Post_CommentRepository post_commentRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public Post_Comment addComment(Long userId, Long postId, Post_Comment comment) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		comment.setUser(user);
		comment.setPost(post);

		return post_commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		// TODO Auto-generated method stub
		Post_Comment comment = post_commentRepository.findById(id).orElse(null);
		post_commentRepository.delete(comment);
	}

	@Override
	public Post_Comment updateComment(Post_Comment comment) {
		// TODO Auto-generated method stub
		return post_commentRepository.save(comment);
	}

	@Override
	public List<Post_Comment> retrievePostComments(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Comment> comments = post_commentRepository.findByPost(post);

		return comments;
	}

}
