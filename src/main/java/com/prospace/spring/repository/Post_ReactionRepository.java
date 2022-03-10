package com.prospace.spring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Comment;
import com.prospace.spring.entity.Post_Reaction;
import com.prospace.spring.entity.Section;
import com.prospace.spring.entity.Topic;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.postReactionType;

@Repository
public interface Post_ReactionRepository extends JpaRepository<Post_Reaction, Long> {
	List<Post_Reaction> findByPost(Post post);

	List<Post_Reaction> findByPostComment(Post_Comment postComment);

	List<Post_Reaction> findByTopic(Topic topic);

	List<Post_Reaction> findBySection(Section section);

	List<Post_Reaction> findByPostAndType(Post post, postReactionType reactionType);

	Post_Reaction findByType(postReactionType type);

	@Query("select R.post from Post_Reaction R  ")
	Set<Post> getReactedpost();

	/**************************************************/

	@Query(value = "SELECT R,S,T FROM Post_Reaction R " + "JOIN User U ON R.user.idUser=U.idUser"
			+ " JOIN Section S ON R.user.idUser=U.idUser " + "LEFT JOIN Topic T ON R.user.idUser=U.idUser "
			+ "WHERE R.type='Like'")
	List<Object[]> SortbyLikes();

	/********************************* GET ACTIVE USERS ************/
	@Query("select R.idReaction.user from Post_Reaction R")
	List<User> getActiveUsers();

}
