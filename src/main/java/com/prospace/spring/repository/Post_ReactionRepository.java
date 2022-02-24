package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Reaction;
import com.prospace.spring.entity.postReactionType;

@Repository
public interface Post_ReactionRepository extends JpaRepository<Post_Reaction, Long> {
	List<Post_Reaction> findByPost(Post post);

	List<Post_Reaction> findByPostAndType(Post post, postReactionType reactionType);
}
