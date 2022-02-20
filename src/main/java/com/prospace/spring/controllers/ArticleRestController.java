package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@ApiOperation(value = "Ajouter client")
	@PostMapping("/add-article") 
	public Article addArticle(@RequestBody Article a) {
		
		return articleService.addArticle(a);
	}
	
	
}
