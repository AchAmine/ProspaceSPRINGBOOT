package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Post_Comment;

public interface IServicePost_Comment {
	Post_Comment addComment(Post_Comment comment,Long userId, Long postId);

	void deleteComment(Long id);

	Post_Comment updateComment(Post_Comment comment);

	List<Post_Comment> retrievePostComments(Long postId);

	/************************************/
	String GetCensoredText(final String content);

	void loadBadWords();

	/************************* Replies **/
	Post_Comment addCommentReply(Long commentId, Long userId, Post_Comment comment);

	List<Post_Comment> retrievePostCommentReplies(Long commentId);
	Post_Comment updateCommentReply(Post_Comment comment);

} 
