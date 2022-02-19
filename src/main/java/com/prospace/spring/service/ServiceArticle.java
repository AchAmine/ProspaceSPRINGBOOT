package com.prospace.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.repository.ArticleRepository;

@Service
public class ServiceArticle implements IServiceArticle{
	@Autowired
	ArticleRepository articleRepository;

}
