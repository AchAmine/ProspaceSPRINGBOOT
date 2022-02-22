package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Reaction;
import com.prospace.spring.entity.ReactionType;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>{

	List<Reaction> findByArticle(Article article);
	List<Reaction> findByArticleAndType(Article article,ReactionType reactionType); 
	
	/*@Query(value = "SELECT r FROM Reaction r WHERE r.type = :reactionType and r.Article = :article")
	List<Reaction> retrieveReactionsByArticleAndType(@Param("article") Article article,
			@Param("reactionType") ReactionType reactionType);*/
}
