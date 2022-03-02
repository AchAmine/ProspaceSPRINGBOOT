package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.User;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("Select u from User u where u.isDeleted= 0")
	public List<User>  getUndeletedUsers();
	public List<User> findByUserName(String userName);
	@Query("Select u from User u where u.userName= :urname")
	public User findOneByUserName(@Param("urname")String urname );
	
	User findByEmail(String email);
	
}
