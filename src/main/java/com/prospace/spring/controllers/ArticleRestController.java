package com.prospace.spring.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prospace.spring.entity.Article;
import com.prospace.spring.service.IServiceArticle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(tags="Article management")
@RequestMapping("/Article")

public class ArticleRestController {

	@Autowired
	IServiceArticle articleService;
	
	@ApiOperation(value = "Add article")
	@PostMapping("/add-article/{user-id}") 
	public Article addArticle(@RequestParam("article") String article, @PathVariable("user-id") Long userId,
			@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
		Article a = new ObjectMapper().readValue(article, Article.class);
		return articleService.addArticle(a,userId,file);
	}
	
	
	@ApiOperation(value = "Delete article")
	@DeleteMapping("/remove-article/{article-id}")
	public void removeArticle(@PathVariable("article-id") Long articleId) {
		articleService.deleteArticle(articleId);
	}
	
	/*
	 * @ApiOperation(value = "Update article")
	 * 
	 * @PutMapping("/modify-article") public Article modifyArticle(@RequestBody
	 * Article article) { return articleService.updateArticle(article); }
	 */
	
	@ApiOperation(value = "Update article")
	@PutMapping("/modify-article")
	public Article modifyArticle(@RequestParam("article") String article,
			@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
		Article a = new ObjectMapper().readValue(article, Article.class);
		return articleService.updateArticle(a,file);
	}
	
	
	@ApiOperation(value = "retrieve all articles")
	@GetMapping("/retrieve-all-articles")
	public List<Article> getArticles(){
		List<Article> listArticles = articleService.retrieveAllArticles();
		return listArticles;
	}
	
	@ApiOperation(value = "retrieve all articles ordered by date")
	@GetMapping("/retrieve-all-articles-ordered")
	public List<Article> retrieveOrderedByDate(){
		List<Article> listArticles = articleService.retrieveOrderedByDate();
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
	
	
	@ApiOperation(value = "retrieve user's following articles")
	@GetMapping("/followingarticles/user/{user-id}")
	public List<Article> FollowingArticles(@PathVariable("user-id") Long userId) {
		return articleService.FollowingArticles(userId);
	}
}
