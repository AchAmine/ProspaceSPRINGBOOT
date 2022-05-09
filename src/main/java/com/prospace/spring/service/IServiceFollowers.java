package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.User;

public interface IServiceFollowers {
	void addFollow(Long idFollower , Long idUser);
	boolean removeFollow (Long idFollower , Long idUser);
	List<User> showFollowers(Long idUser);
	
}
