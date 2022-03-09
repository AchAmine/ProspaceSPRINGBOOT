package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.entity.Response;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	
	
	
	@Query(value = "SELECT count(*) FROM Question q JOIN quizz qu WHERE qu.id_quizz= :quizz" ,
			nativeQuery = true)
			
	int NbQuestions(@Param("quizz") Quizz quizz);
	//somme des reponses par question
	//	@Query("SELECT sum(nbreptsanswer) FROM Question q left join q.Answers where q.idQuestion = :idquestion")
	
	
	@Query(value = "SELECT SUM(a.nbreptsanswer) FROM question q JOIN answer a where q.id_question= :idquestion" ,
			nativeQuery = true)
			
			float NbPtsQuestion(@Param("idquestion")Long idquestion);
	
	
	
}
