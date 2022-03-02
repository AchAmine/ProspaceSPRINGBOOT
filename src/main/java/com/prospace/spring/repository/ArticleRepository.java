package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

	List<Article> findByUser(User user);
}
