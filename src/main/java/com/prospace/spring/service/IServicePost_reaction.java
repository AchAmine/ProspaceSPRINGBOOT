package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Post_Reaction;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.postReactionType;

public interface IServicePost_reaction {
	Post_Reaction addPost_Reaction(Long userId, Long postId, Post_Reaction reaction);

	void deletePostReaction(Long id);

	Post_Reaction updatePostReaction(Post_Reaction reaction);

	List<Post_Reaction> retrievePostReactions(Long postId);

	/*****************************************************************/

	Post_Reaction addPostComment_Reaction(Long userId, Long commentId, Post_Reaction reaction);

	List<Post_Reaction> retrievePostCommentReactions(Long commentId);

	/******************************************************************/

	Post_Reaction addSectionReaction(Long userId, Long sectionId, Post_Reaction reaction);

	List<Post_Reaction> retrieveSectionReactions(Long sectionId);

	/*******************************************************************/
	Post_Reaction addTopicReaction(Long userId, Long topicId, Post_Reaction reaction);

	List<Post_Reaction> retrieveTopicReactions(Long topicId);

	List<User> retrievePostReactors(Long postId);

	List<User> retrievePostReactorsByType(Long postId, postReactionType reactionType);

	/*******************************************************************/
	
	/******************************************************************/

	/******************************************************************/
	List<Object[]> SortbyLikes(Long userId);

}
