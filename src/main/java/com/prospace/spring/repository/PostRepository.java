package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUser(User user);

	List<Post> findTop5ByOrderByCreatedAtDesc();

	@Query(value = "SELECT P FROM Post P INNER JOIN Post_Reaction R ON R.post.idPost=P.idPost WHERE R.type='Like' ")
	List<Post> findByPostReactions();

}
