package com.prospace.spring.service;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.User;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceUser implements IServiceUser {
	@Autowired
	UserRepository userRepository;
		@Override
		public void addUser(User u) {
			Date date = new Date(System.currentTimeMillis());
			u.setCreatedAt(date);
			 Period period = Period.between( u.getBirthDate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate(),date.toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
			u.setAge(period.getYears());
			userRepository.save(u);
			
		}
		@Override
		public List<User> getusers() {
			
			
			return userRepository.findAll() ;
		}
		@Override
		public List<User> getUndeletedUsers() {
		
			return userRepository.getUndeletedUsers();
		}
		@Override
		public void deleteUser(Long id) {
			Date date = new Date(System.currentTimeMillis());
			User deletedUser = userRepository.findById(id).orElse(null);
			deletedUser.setDeleted(true);
			deletedUser.setDeletedAt(date);
			userRepository.save(deletedUser);
			
		}
		@Override
		public void updateUser(User u) {
			Date date = new Date(System.currentTimeMillis());
			 Period period = Period.between( u.getBirthDate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate(),date.toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
			u.setAge(period.getYears());
			u.setModifiedAt(date);
			userRepository.save(u);
			
		}

}
