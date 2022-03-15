package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.User;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FollowersService implements IServiceFollowers{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public void addFollow(Long idFollower, Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		User follower = userRepository.findById(idFollower).orElse(null);
		log.info("------User  : "+user.getIdUser());
		log.info("------Follower : "+follower.getIdUser());
		user.getFollowers().add(follower);
		log.info("---------List size " +user.getFollowers().size());
		userRepository.save(user);
		
		
	}


	@Override
	public boolean removeFollow(Long idFollower, Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		User follower = userRepository.findById(idFollower).orElse(null);
		boolean removed = false;
		removed = user.getFollowers().remove(follower);
		userRepository.save(user);
		return removed;
	}


	@Override
	public List<User> showFollowers(Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		List<User> listFollowers = new ArrayList<User>();
		
		for (User u : user.getFollowers()) {
			listFollowers.add(u);
		}
		
		return listFollowers;
	}


}
