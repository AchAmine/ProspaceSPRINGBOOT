package com.prospace.spring.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.ArticleType;
import com.prospace.spring.entity.Article_Comment;
import com.prospace.spring.entity.Image;
import com.prospace.spring.entity.Reaction;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.ArticleRepository;
import com.prospace.spring.repository.Article_CommentRepository;
import com.prospace.spring.repository.ReactionRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceArticle implements IServiceArticle{
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	IServiceImage imageService;
	
	@Autowired
	Article_CommentRepository articleCommentRepository;
	@Autowired
	ReactionRepository reactionRepository;
	
	@Override
	@Transactional
	public Article addArticle(Article A,Long idUser,MultipartFile file) {
		/*
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		String username = ((UserDetails)principal).getUsername();
		} else {
		String username = principal.toString();
		}
		*/
		
		User user = userRepository.findById(idUser).orElse(null);
		A.setUser(user);
		Date date = new Date(System.currentTimeMillis());
		A.setCreatedAt(date);
		user.getArticles().add(A);
	/*	Set<Article> listArticles = user.getArticles();
		listArticles.add(A);
		user.setArticles(listArticles); */
		
		//Image
		Image image = new Image(file.getOriginalFilename());
		A.setImage(image);
		imageService.save(file);
				//EndImage
				
		return articleRepository.save(A);
	}

	@Override
	@Transactional
	public void deleteArticle(Long id) {
		Article article = articleRepository.findById(id).orElse(null);
		log.info(" -------------- delete article ID : "+id);
		log.info(" -------------- delete article : "+article);
		//articleRepository.delete(article);
		//
		int del = articleRepository.deleteArticle(id);
		List<Article_Comment> comments = articleCommentRepository.findByArticle(article);
		articleCommentRepository.deleteAll(comments);
		
		List<Reaction> reactions = reactionRepository.findByArticle(article);
		reactionRepository.deleteAll(reactions);
		
		//
		log.info("after delete");
		log.info("del : "+del);
		//articleRepository.deleteById(id);
		
	}

	/*
	 * @Override public Article updateArticle(Article A) { User user =
	 * userRepository.findById(A.getIdArticle()).orElse(null); Article article =
	 * articleRepository.findById(A.getIdArticle()).orElse(null); A.setUser(user);
	 * A.setCreatedAt(article.getCreatedAt()); if (A.getImage() == null ) {
	 * A.setImage(article.getImage()); } Date date = new
	 * Date(System.currentTimeMillis()); A.setUpdatedAt(date); return
	 * articleRepository.save(A); }
	 */
	
	@Override
	public Article updateArticle(Article A,MultipartFile file) {
		User user = userRepository.findById(A.getIdArticle()).orElse(null);
		Article article = articleRepository.findById(A.getIdArticle()).orElse(null);
		A.setUser(user);
		A.setCreatedAt(article.getCreatedAt());

		Date date = new Date(System.currentTimeMillis());
		A.setUpdatedAt(date);
		
		if (A.getImage() == null ) {
			A.setImage(article.getImage());
		}
		//Image
				if (file!=null) {
				Image image = new Image(file.getOriginalFilename());
				A.setImage(image);
				imageService.save(file);
				} else {
					A.setImage(article.getImage());
				}
						//EndImage
				A.setType(ArticleType.News);
				
		return articleRepository.save(A);
	}
	

	@Override
	public List<Article> retrieveAllArticles() {
		return articleRepository.findAll();
	}
	
	@Override
	public Article retrieveArticle(Long id) {
		return articleRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Article> retrieveOrderedByDate() {
		return articleRepository.retrieveOrderedByDate();
	}

	@Override
	public List<Article> getArticlesByUser(Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		List<Article> listArticles = articleRepository.findByUser(user);
		
		// 2 eme methode 
		/*
		List<Article> listArticles = new ArrayList<Article>();
		listArticles.addAll(user.getArticles());
		*/		
		return listArticles;
	}

	@Override
	public Article EnableComments(Long idArticle, Boolean action) {
		Article article = articleRepository.findById(idArticle).orElse(null);
		article.setEnableComments(action);
		
		return articleRepository.save(article);
		
	}

	@Override
	public HashMap<Long, Long> SortByReaction(Long idUser ) {
		HashMap<Long, Long> hMap = new HashMap<Long, Long>();
		List<Object[]> listStaff = articleRepository.SortByReaction(idUser);
		for (Object[] obj : listStaff) {
			log.info("------- ADMIN --------------"+(Long)obj[0]);
			log.info("------- ARTICLE --------------"+(Long)obj[1]);
			if (hMap.containsKey((Long)obj[0])) {
				hMap.put( (Long)obj[0] , hMap.get((Long)obj[0])+1);
			} else {
			hMap.put( (Long)obj[0], 1L);
			}
		}
		return hMap;
	}

	@Override
	public HashMap<Long, Long> SortByComments(Long idUser) {
		HashMap<Long, Long> hMap = new HashMap<Long, Long>();
		List<Object[]> listStaff = articleRepository.SortByComments(idUser);
		for (Object[] obj : listStaff) {
			log.info("------- ADMIN --------------"+(Long)obj[0]);
			log.info("------- ARTICLE --------------"+(Long)obj[1]);
			if (hMap.containsKey((Long)obj[0])) {
				hMap.put( (Long)obj[0] , hMap.get((Long)obj[0])+1);
			} else {
			hMap.put( (Long)obj[0], 1L);
			}
		}
		return hMap;
	}

	@Override
	public HashMap<Long, Long> userPreferences(Long idUser) {
		
		HashMap<Long, Long> commenthMap = SortByComments(idUser);
		HashMap<Long, Long> reactionhMap = SortByReaction(idUser );
	
		log.info("--------- commentmap size"+commenthMap.size());
		log.info("--------- reactionmap size"+reactionhMap.size());
		if (commenthMap.size() > reactionhMap.size()) {
			log.info("--------- MERGING INTO COMMENTHMAP");
			reactionhMap.forEach(
					  (key, value) -> commenthMap.merge(key, value, (v1, v2) -> v1 + v2));
			log.info("MAP ------- 1"+commenthMap);
			return commenthMap;
		} else {
			log.info("--------- MERGING INTO REACTIONHMAP");
			commenthMap.forEach(
					  (key, value) -> reactionhMap.merge(key, value, (v1, v2) -> v1 + v2));
			log.info("MAP ------ 2"+reactionhMap);
			return reactionhMap;
		}	
	}
	
	@Override
	public List<Article> userPreferencesArticles(Long idUser) {
		List<Article> articles = new ArrayList<Article>();
		HashMap<Long,Long> userPref = this.userPreferences(idUser);
		for(Map.Entry user : userPref.entrySet()) {
			Long userId = (Long) user.getKey();
			articles.addAll(this.getArticlesByUser(userId));
		}
		
		articles.sort(Comparator.comparing(Article::getViews).reversed());
		return articles;
	}

	@Override
	public List<Article> FollowingArticles(Long idUser) {
		User u = userRepository.findById(idUser).orElse(null);
		List<User> followers = new ArrayList<User>();
		
		for (User user : u.getFollowers()) {
			followers.add(user);
		}
		return articleRepository.FollowingArticles(followers);
	}

	@Override
	public Article viewIncrement(Article article) {
		Article article1 = articleRepository.findById(article.getIdArticle()).orElse(null);
		log.info("------------------Article Before inc"+article1);
		log.info("-----------------Views Before inc"+article1.getViews());
		article1.setViews(article1.getViews() + 1); 
		log.info("-----------------Views After inc"+article1.getViews());
		return articleRepository.save(article1);
	}

	

	
}
