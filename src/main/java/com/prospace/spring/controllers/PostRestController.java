package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Post;
import com.prospace.spring.service.IServicePost;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Post management")
@RequestMapping("/Post")
public class PostRestController {
	@Autowired
	IServicePost postService;
	
	
	@ApiOperation(value = "Show all posts")
	@GetMapping("/find-all-posts")
	public List<Post> findAll(){
		List<Post> listPosts = postService.findAll();
		return listPosts;
	}
	
	
	@ApiOperation(value = "Show one post")
	@GetMapping("/find-post/{post-id}")
	public Post findPost(@PathVariable("post-id") Long idPost) {
		
		return postService.findOne(idPost);
	}
	
	@ApiOperation(value = "add post")
	@PostMapping("/add-post/{user-id}") 
	public Post addPost(@RequestBody Post post,@PathVariable("user-id")Long idUser) {
		
		return postService.save(post,idUser);
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
	
	
	/*@ApiOperation(value = " add a post to a topic")
	@PutMapping("/add-to-topic/{post-id}/post/{post-id}") 
	public void assignPostToTopic(@PathVariable("post-id") Long idPost,@PathVariable("stock-id") Long idStock) {
		 stockService.assignProduitToStock(idProduit, idStock);
	}*/
}
