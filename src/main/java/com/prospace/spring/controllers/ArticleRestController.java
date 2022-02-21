package com.prospace.spring.controllers;

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
	
	@ApiOperation(value = "Ajouter article")
	@PostMapping("/add-article/{user-id}") 
	public Article addArticle(@RequestBody Article a, @PathVariable("user-id") Long userId) {
		
		return articleService.addArticle(a,userId);
	}
	
	
	@ApiOperation(value = "Supprimer un article")
	@DeleteMapping("/remove-article/{article-id}")
	public void removeArticle(@PathVariable("article-id") Long articleId) {
		articleService.deleteArticle(articleId);
	}
	
	@ApiOperation(value = "Modifier un article")
	@PutMapping("/modify-article")
	public Article modifyArticle(@RequestBody Article article) {
		return articleService.updateArticle(article);
	}
	
	@ApiOperation(value = "afficher la liste des articles")
	@GetMapping("/retrieve-all-articles")
	public List<Article> getArticles(){
		List<Article> listArticles = articleService.retrieveAllArticles();
		return listArticles;
	}
	
	@GetMapping("/retrieve-article/{article-id}")
	public Article retrieveArticle(@PathVariable("article-id") Long articleId) {
		return articleService.retrieveArticle(articleId);
	}
	
	@GetMapping("/retrieve-article/user/{user-id}")
	public List<Article> retrieveUserArticles(@PathVariable("user-id") Long userId) {
		return articleService.getArticlesByUser(userId);
	}
	
}
