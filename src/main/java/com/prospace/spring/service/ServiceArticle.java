package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Article;
import com.prospace.spring.repository.ArticleRepository;

@Service
public class ServiceArticle implements IServiceArticle{
	@Autowired
	ArticleRepository articleRepository;

	

}
