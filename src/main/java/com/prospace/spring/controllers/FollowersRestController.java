package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.User;
import com.prospace.spring.service.IServiceFollowers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Followers management")
@RequestMapping("/followers")
public class FollowersRestController {

	@Autowired
	IServiceFollowers followersService;
	
	@ApiOperation(value = "Add follower")
	@PostMapping("/{user-id}/add-follower/{follower-id}") 
	public void addFollow(@PathVariable("user-id") Long userId,@PathVariable("follower-id") Long followerId) {
		
		  followersService.addFollow(followerId, userId);
	}
	
	@ApiOperation(value = "Remove follow")
	@DeleteMapping("/{user-id}/remove-follower/{follower-id}")
	public boolean removeFollow(@PathVariable("user-id") Long userId,@PathVariable("follower-id") Long followerId) {
		
		  return followersService.removeFollow(followerId, userId);
	}
	
	
	@ApiOperation(value = "retrieve all followers")
	@GetMapping("/listfollowers/{user-id}")
	public List<User> retriveFollowers(@PathVariable("user-id") Long userId) {
		
		return  followersService.showFollowers(userId);
	}
	
	
	
}
