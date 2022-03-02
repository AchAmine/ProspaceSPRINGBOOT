package com.prospace.spring.service;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Article_Comment;
import com.prospace.spring.entity.Badwords;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ArticleRepository;
import com.prospace.spring.repository.Article_CommentRepository;
import com.prospace.spring.repository.BadwordsRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceArticle_Comment implements IServiceArticle_Comment{

	@Autowired
	Article_CommentRepository article_commentRepository ; 
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BadwordsRepository badwordsRepository;
	
	@Override
	public Article_Comment addComment(Long userId , Long articleId , Article_Comment comment) {
		Article article = articleRepository.findById(articleId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		comment.setUser(user);
		comment.setArticle(article);
		
		return article_commentRepository.save(comment);
	}

	@Override
	public void deleteComment(Long id) {
		Article_Comment comment = article_commentRepository.findById(id).orElse(null);
		article_commentRepository.delete(comment);	
	}

	@Override
	public Article_Comment updateComment(Article_Comment comment) {
		Article_Comment artcom = article_commentRepository.findById(comment.getIdComment()).orElse(null);
		comment.setArticle(artcom.getArticle());
		comment.setUser(artcom.getUser());
		return article_commentRepository.save(comment);
	}

	@Override
	public List<Article_Comment> retrieveArticleComments(Long articleId) {
		Article article = articleRepository.findById(articleId).orElse(null);
		List<Article_Comment> comments =  article_commentRepository.findByArticle(article);
		List<Article_Comment> censoredComments = new ArrayList<Article_Comment>();
		for(int i = 0 ; i<comments.size();i++) {
			comments.get(i).setContent(CensoredWords(comments.get(i).getContent()));
		}
		return comments;
	}

	@Override
	public String CensoredWords(String comment) {
		List<Badwords> wordsList = badwordsRepository.findAll();
		List<String> words = new ArrayList<String>();
		
		log.info("wordsList size : "+wordsList.size());
		for(int i=0 ; i< wordsList.size();i++) {
			words.add(wordsList.get(i).getWord());
		}
		
		log.info("words size : "+words.size());
		
		for (String word : words) {
            Pattern rx = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
            comment = rx.matcher(comment).replaceAll(new String(new char[word.length()]).replace('\0', '*'));
           
		}
		log.info("New comment  "+comment);
		
		
		return comment;
	}
	
	

}
