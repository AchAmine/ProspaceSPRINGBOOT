package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.Post_Comment;
import com.prospace.spring.entity.Post_Reaction;
import com.prospace.spring.entity.Section;
import com.prospace.spring.entity.Topic;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.postReactionType;
import com.prospace.spring.repository.PostRepository;
import com.prospace.spring.repository.Post_CommentRepository;
import com.prospace.spring.repository.Post_ReactionRepository;
import com.prospace.spring.repository.SectionRepository;
import com.prospace.spring.repository.TopicRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServicePost_Reaction implements IServicePost_reaction {
	@Autowired
	Post_ReactionRepository postReactionRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Post_CommentRepository pCommRepository;
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	TopicRepository topicRepository;

	/********************* Post Reactions ********************/
	@Override
	public Post_Reaction addPost_Reaction(Long userId, Long postId, Post_Reaction reaction) {
		Post post = postRepository.findById(postId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);

		reaction.setPost(post);
		reaction.setUser(user);
		return postReactionRepository.save(reaction);
	}

	@Override
	public void deletePostReaction(Long id) {
		Post_Reaction reaction = postReactionRepository.findById(id).orElse(null);
		postReactionRepository.delete(reaction);

	}

	@Override
	public Post_Reaction updatePostReaction(Post_Reaction reaction) {
		// TODO Auto-generated method stub
		return postReactionRepository.save(reaction);
	}

	@Override
	public List<Post_Reaction> retrievePostReactions(Long postId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Reaction> reactions = postReactionRepository.findByPost(post);

		return reactions;
	}

	/************** End PostReactions ************/

	/********************* post comments Reactions ********************/
	@Override
	public Post_Reaction addPostComment_Reaction(Long userId, Long commentId, Post_Reaction reaction) {
		Post_Comment postComment = pCommRepository.findById(commentId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);

		reaction.setPostComment(postComment);
		reaction.setUser(user);
		return postReactionRepository.save(reaction);
	}

	@Override
	public List<Post_Reaction> retrievePostCommentReactions(Long commentId) {
		// TODO Auto-generated method stub
		Post_Comment postComment = pCommRepository.findById(commentId).orElse(null);
		List<Post_Reaction> reactions = postReactionRepository.findByPostComment(postComment);

		return reactions;
	}

	/************** End Reactions ************/

	/********************* SECTION Reactions ********************/
//	@Override
//	public Post_Reaction addSectionReaction(Long userId, Long sectionId, Post_Reaction reaction) {
//		Section section = sectionRepository.findById(sectionId).orElse(null);
//		User user = userRepository.findById(userId).orElse(null);
//
//		reaction.setSection(section);
//		reaction.setUser(user);
//		return postReactionRepository.save(reaction);
//	}
//
//	@Override
//	public List<Post_Reaction> retrieveSectionReactions(Long sectionId) {
//		// TODO Auto-generated method stub
//		Section section = sectionRepository.findById(sectionId).orElse(null);
//		List<Post_Reaction> reactions = postReactionRepository.findBySection(section);
//
//		return reactions;
//	}

	/************** End Reactions ************/

	/********************* TOPIC Reactions ********************/
//	@Override
//	public Post_Reaction addTopicReaction(Long userId, Long topicId, Post_Reaction reaction) {
//		Topic topic = topicRepository.findById(topicId).orElse(null);
//		User user = userRepository.findById(userId).orElse(null);
//
//		reaction.setTopic(topic);
//		reaction.setUser(user);
//		return postReactionRepository.save(reaction);
//	}
//
//	@Override
//	public List<Post_Reaction> retrieveTopicReactions(Long topicId) {
//		// TODO Auto-generated method stub
//		Topic topic = topicRepository.findById(topicId).orElse(null);
//		List<Post_Reaction> reactions = postReactionRepository.findByTopic(topic);
//
//		return reactions;
//	}

	/************** End Reactions ************/

	@Override
	public List<User> retrievePostReactors(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Reaction> reactions = postReactionRepository.findByPost(post);
		List<User> users = new ArrayList<>();

		for (Post_Reaction react : reactions) {
			users.add(react.getUser());
		}
		return users;
	}

	@Override
	public List<User> retrievePostReactorsByType(Long postId, postReactionType reactionType) {
		Post post = postRepository.findById(postId).orElse(null);
		List<Post_Reaction> reactions = postReactionRepository.findByPostAndType(post, reactionType);

		List<User> users = new ArrayList<>();

		for (Post_Reaction react : reactions) {
			users.add(react.getUser());
		}
		return users;
	}

	/************************/
//
//@Override
//	public List<Object[]> SortbyLikes(Long userId) {
//		return postReactionRepository.SortbyLikes(userId);
//
//	}
}
