package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Reaction;
import com.prospace.spring.entity.ReactionType;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long>{

	List<Reaction> findByArticle(Article article);
	List<Reaction> findByArticleAndReactionType(Article article,ReactionType reactionType); 
}
