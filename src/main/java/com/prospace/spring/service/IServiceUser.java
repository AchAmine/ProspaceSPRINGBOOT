package com.prospace.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prospace.spring.entity.Experience;
import com.prospace.spring.entity.Skill;
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
	public String getTitreIng();
	public User getOne(String u);
	public User getProfile(Long id);
	public Long getIdUSer(String username);
	public void affecterSkillToUser(Long ids,Long idu);
	public void affecterExpToSkill(Long ids,Long ide);
	public Long saveExpe(Experience e);
	public Long saveSkill(Skill s);
	public void updateUser2(Skill s,Long idu,Long ide);
}
