package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Post_Comment;

@Repository
public interface Post_CommentRepository extends JpaRepository<Post_Comment, Long>{

}
