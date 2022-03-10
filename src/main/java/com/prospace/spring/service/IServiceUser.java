package com.prospace.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prospace.spring.entity.User;

public interface IServiceUser {
	String addUser(User u);
	List<User> getusers();
	List<User> getUndeletedUsers();
	void deleteUser(Long id);
	void updateUser(User u);
	void send(String to, String email);
	public String confirmToken(String token);
	public String forgotPassword(String userName,HttpServletRequest request);
	public String fogetPasswordSetting(String token,String newPass);
}
