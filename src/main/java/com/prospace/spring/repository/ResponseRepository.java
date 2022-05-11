package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.Response;
import com.prospace.spring.entity.User;
import java.util.List;
@Repository
public interface ResponseRepository  extends JpaRepository<Response,Long>{
List<Response> findByUserresponse(User userresponse);

		@Query("SELECT DISTINCT count(*) FROM Response r ")
		int NbResponsesQuizz();
		
		@Query("SELECT r from Response r WHERE r.userresponse.idUser = :userId AND r.quizz.idQuizz = :quizzId")
		List<Response> userResponses(@Param("quizzId") Long quizzId ,
				@Param("userId") Long userId);
	@Query(value = "SELECT userresponse_id_user FROM response" ,
				nativeQuery = true)
		List<Long> iduserresponses();
}
