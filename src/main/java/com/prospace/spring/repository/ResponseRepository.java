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
}
