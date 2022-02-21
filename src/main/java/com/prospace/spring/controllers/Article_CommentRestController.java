package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Article_Comment;
import com.prospace.spring.service.IServiceArticle_Comment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Article comments management")
@RequestMapping("/Article_comments")
public class Article_CommentRestController {

	@Autowired
	IServiceArticle_Comment article_commentService;
	
	@ApiOperation(value = "Ajouter commentaire")
	@PostMapping("/add-comment/{article-id}/{user-id}") 
	public Article_Comment addComment(@RequestBody Article_Comment comment, @PathVariable("article-id") Long articleId,
			@PathVariable("user-id") Long userId) {
		
		return article_commentService.addComment(userId,articleId,comment);
	}
	
	
	@ApiOperation(value = "Supprimer un commentaire")
	@DeleteMapping("/remove-comment/{comment-id}")
	public void removeComment(@PathVariable("comment-id") Long commentId) {
		article_commentService.deleteComment(commentId);
	}
	
}
