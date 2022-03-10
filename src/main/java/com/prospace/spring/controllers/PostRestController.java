package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Post;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.PostRepository;
import com.prospace.spring.service.IServicePost;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Post management")
@RequestMapping("/Post")
public class PostRestController {
	@Autowired
	IServicePost postService;
	@Autowired
	PostRepository postRepository;

	@ApiOperation(value = "Show all posts")
	@GetMapping("/find-all-posts")
	public List<Post> findAll() {
		List<Post> listPosts = postService.findAll();
		return listPosts;
	}

	@ApiOperation(value = "Show one post")
	@GetMapping("/find-post/{post-id}")
	public Post findPost(@PathVariable("post-id") Long idPost) {

		return postService.findOne(idPost);
	}

	@ApiOperation(value = "add post")
	@PostMapping("/add-post/{user-id}/{topic-id}")
	public Post addPost(@RequestBody Post post, @PathVariable("user-id") Long idUser,@PathVariable("topic-id") Long topicId) {

		return postService.save(post, idUser,topicId);
	}

	@ApiOperation(value = "Delete post")
	@DeleteMapping("/remove-post/{post-id}")
	public void delete(@PathVariable("post-id") Long idPost) {
		postService.delete(idPost);
	}

	@PutMapping("/modify-post")
	public Post update(@RequestBody Post post) {
		return postService.updatePost(post);
	}

	@ApiOperation(value = "Show Recent posts created at Desc ")
	@GetMapping("/find-recent-posts")
	public List<Post> findRecent() {
		List<Post> listPosts = postService.findRecent();
		return listPosts;
	}

	@ApiOperation(value = "Show posts by user ")
	@GetMapping("/find-posts-byUser/{user-id}")
	public List<Post> findByUser(@PathVariable("user-id") User user) {
		List<Post> listPosts = postService.findByUser(user);
		return listPosts;
	}

	/***** PAGINATION and SORTING *****/
	// http://localhost:8089/SpringMVC/Post/listPageable?page=0&size=2&sort=title
	@ApiOperation(value = "Show posts sorted in pages  ")
	@RequestMapping(value = "/listPageable", method = RequestMethod.GET)
	Page<Post> Pageable(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@ApiOperation(value = "Show Liked Posts ")
	@GetMapping("/find-bypostReaction")
	public List<Post> findbyReaction() {
		return postService.findByPostReactions();
	}
	/******************************************/

}
