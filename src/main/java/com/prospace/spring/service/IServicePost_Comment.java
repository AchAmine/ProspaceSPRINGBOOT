package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Post_Comment;

public interface IServicePost_Comment {
	Post_Comment addComment(Long userId, Long postId, Post_Comment comment);

	void deleteComment(Long id);

	Post_Comment updateComment(Post_Comment comment);

	List<Post_Comment> retrievePostComments(Long postId);

}
