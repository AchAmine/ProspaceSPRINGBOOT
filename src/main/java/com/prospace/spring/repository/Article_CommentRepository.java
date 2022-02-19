package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Article_Comment;

@Repository
public interface Article_CommentRepository extends JpaRepository<Article_Comment, Long>{

}
