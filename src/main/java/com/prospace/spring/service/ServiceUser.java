package com.prospace.spring.service;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.prospace.spring.entity.User;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceUser implements IServiceUser {
	@Autowired
	UserRepository userRepository;
	/*@Autowired
    PasswordEncoder encoder;*/
	/*

	@Override
	public void addUser(User u) {
		Date date = new Date(System.currentTimeMillis());
		User searchedUser=null;
		try {
			log.info("in method addUser");
			searchedUser=userRepository.findOneByUserName(u.getUserName());
			if(searchedUser==null) 
			                     {
				log.info("userName dont exist");
			u.setCreatedAt(date);
			Period period = Period.between(u.getBirthDate()
					                        .toInstant()
					                        .atZone(ZoneId.systemDefault())
					                        .toLocalDate(),
					                       date
					                        .toInstant()
					                        .atZone(ZoneId.systemDefault())
					                        .toLocalDate());
			u.setAge(period.getYears());
			//u.setPassword(encoder.encode(u.getPassword()));
			userRepository.save(u);
			}
			else {log.info("userName already exist!!");}
			log.info("out of method addUser without errors");
		} catch (Exception e) {
			log.error("error in method addUser" + e);
		}

	}

	@Override
	public List<User> getusers() {
		List<User> searchedUsers = null;
		try {
			log.info("in method getUsers");
			searchedUsers = userRepository.findAll();
			log.info("out of  method getUsers without errors");
		} catch (Exception e) {
			log.error("error in method getUsers" + e);
		}
		return searchedUsers;
	}

	@Override
	public List<User> getUndeletedUsers() {
		List<User> undeletedUsers = null;
		try {
			log.info("in method getUndeletedUsers");
			undeletedUsers = userRepository.getUndeletedUsers();
			log.info("out of  method getUndeletedUsers without errors");
		} catch (Exception e) {
			log.error("error in method getUndeletedUsers" + e);
		}
		return undeletedUsers;
	}

	@Override
	public void deleteUser(Long id) {
		Date date = new Date(System.currentTimeMillis());
		try {
			log.info("in method deleteUser");
			User deletedUser = userRepository.findById(id).orElse(null);
			deletedUser.setDeleted(true);
			deletedUser.setDeletedAt(date);
			userRepository.save(deletedUser);
			log.info("out of  method deleteUser without errors");
		} catch (Exception e) {
			log.error("error in method deleteUser" + e);
		}

	}

	@Override
	public void updateUser(User u) {
		Date date = new Date(System.currentTimeMillis());
		try {
			log.info("in method updateUser");
			Period period = Period.between(u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			u.setAge(period.getYears());
			u.setModifiedAt(date);
			userRepository.save(u);
			log.info("out of  method updateUser without errors");
		} catch (Exception e) {
			log.error("error in method upadateUser" + e);
		}

	}*/

	@Override
	public void addUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getusers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUndeletedUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}

}
