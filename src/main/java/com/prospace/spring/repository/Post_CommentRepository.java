package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Comment;

@Repository
public interface Post_CommentRepository extends JpaRepository<Post_Comment, Long> {
	List<Post_Comment> findByPost(Post post);

}
