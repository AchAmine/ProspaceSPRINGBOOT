package com.prospace.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Comment;

@Repository
public interface Post_CommentRepository extends JpaRepository<Post_Comment, Long> {
	List<Post_Comment> findByPost(Post post);
	@Query(value = "SELECT * FROM Post_Comment pc join pcomm.post.idPost p WHERE p.createdAt=:Date" , nativeQuery = true)
	List<Post> getPostsByDate(@Param("Date") Date Date);
	
	/***********/
	@Query("Select COUNT(*) FROM Post_Comment pc where pc.post.idPost = :idPost")
	int nbreComments(@Param("idPost") Long idPost);
}
