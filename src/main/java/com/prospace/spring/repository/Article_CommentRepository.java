package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Article_Comment;

@Repository
public interface Article_CommentRepository extends JpaRepository<Article_Comment, Long>{

	List<Article_Comment> findByArticle(Article article);
	
	//List<Article_Comment> ArticleComments(Article article);
	
	/*@Query(value = "SELECT A FROM Article_Comment A WHERE r.type = :reactionType and r.Article = :article")
	List<Article_Comment> ArticleComments(@Param("article") Article article,
			@Param("reactionType") Article article);*/
}
