package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Article_Comment;

public interface IServiceArticle_Comment {

	Article_Comment addComment(Long userId , Long articleId , Article_Comment comment);
	void deleteComment(Long id);
	Article_Comment updateComment(Article_Comment comment);
}
