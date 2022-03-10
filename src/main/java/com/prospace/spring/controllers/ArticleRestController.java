package com.prospace.spring.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Article;
import com.prospace.spring.service.IServiceArticle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Article management")
@RequestMapping("/Article")
public class ArticleRestController {

	@Autowired
	IServiceArticle articleService;
	
	@ApiOperation(value = "Add article")
	@PostMapping("/add-article/{user-id}") 
	public Article addArticle(@RequestBody Article a, @PathVariable("user-id") Long userId) {
		
		return articleService.addArticle(a,userId);
	}
	
	
	@ApiOperation(value = "Delete article")
	@DeleteMapping("/remove-article/{article-id}")
	public void removeArticle(@PathVariable("article-id") Long articleId) {
		articleService.deleteArticle(articleId);
	}
	
	@ApiOperation(value = "Update article")
	@PutMapping("/modify-article")
	public Article modifyArticle(@RequestBody Article article) {
		return articleService.updateArticle(article);
	}
	
	@ApiOperation(value = "retrieve all articles")
	@GetMapping("/retrieve-all-articles")
	public List<Article> getArticles(){
		List<Article> listArticles = articleService.retrieveAllArticles();
		return listArticles;
	}
	
	@ApiOperation(value = "retrieve article by id")
	@GetMapping("/retrieve-article/{article-id}")
	public Article retrieveArticle(@PathVariable("article-id") Long articleId) {
		return articleService.retrieveArticle(articleId);
	}
	
	@ApiOperation(value = "retrieve user's articles")
	@GetMapping("/retrieve-article/user/{user-id}")
	public List<Article> retrieveUserArticles(@PathVariable("user-id") Long userId) {
		return articleService.getArticlesByUser(userId);
	}
	
	// ------------------------------------ fct avancée  
	@ApiOperation(value = "sort admins by comments ")
	@GetMapping("/sortbycomments/user/{user-id}")
	public HashMap<Long, Long> SortByComments(@PathVariable("user-id") Long userId) {
		return articleService.SortByComments(userId);
	}
	
	// ------------------------------------
	@ApiOperation(value = "sort admins by reaction ")
	@GetMapping("/sortbyreactions/user/{user-id}")
	public HashMap<Long, Long> SortByReaction(@PathVariable("user-id") Long userId) {
		return articleService.SortByReaction(userId);
	}
	
	@ApiOperation(value = "sort admins by preferences ")
	@GetMapping("/userPreferences/user/{user-id}")
	public HashMap<Long, Long> userPreferences(@PathVariable("user-id") Long userId) {
		return articleService.userPreferences(userId);
	}
	
}
