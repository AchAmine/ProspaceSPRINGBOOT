package com.prospace.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.service.IServiceArticle;

import io.swagger.annotations.Api;

@RestController
@Api(tags="Article management")
@RequestMapping("/Article")
public class ArticleRestController {

	@Autowired
	IServiceArticle articleService;
}
