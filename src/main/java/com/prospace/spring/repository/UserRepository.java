package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("Select u from User u where u.isDeleted= 0")
	public List<User>  getUndeletedUsers();
}
