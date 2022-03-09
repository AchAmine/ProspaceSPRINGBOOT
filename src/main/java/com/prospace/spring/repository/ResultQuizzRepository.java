package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.ResultQuizz;
import com.prospace.spring.entity.User;

@Repository

public interface ResultQuizzRepository extends JpaRepository<ResultQuizz,Long> {
	@Query("SELECT rq FROM ResultQuizz rq where rq.quizz = :quizz and rq.user = :user ")
	ResultQuizz getResultbyquizzanduser(@Param("quizz") Quizz quizz,@Param("user") User user);
	
	@Query(value = "SELECT user.user_name,result_quizz.score FROM user "
			+ "INNER JOIN response "
			+ "INNER JOIN result_quizz ON(response.userresponse_id_user=user.id_user) "
			+ "WHERE result_quizz.quizz_id_quizz= :quizzid "
			+ "ORDER BY result_quizz.score DESC "
			+ "LIMIT 3" ,
			nativeQuery = true)
	List<Object[]> TOP3Result(@Param("quizzid") Long quizzid);
			
			
		
			
		
	
	
	
}
	
