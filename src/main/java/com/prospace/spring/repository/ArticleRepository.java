package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

	List<Article> findByUser(User user);
	
	@Query(value = "SELECT S.idUser , SIZE(S.Articles) "
			+ "FROM User S  "
			+ "INNER JOIN Reaction R "
			+ "ON R.article MEMBER OF S.Articles "
			+ "WHERE R.type = Love OR R.type = Funny And   R.user.idUser = :userId "
			+ "Group By S.idUser  "
			+ "Order By SIZE(S.Articles) DESC")
			List<Object[]> SortByReaction(@Param("userId") Long userId );
	
			
	@Query(value = "SELECT S.idUser , SIZE(S.Articles) "
			+ "FROM User S "
			+ "INNER JOIN Article_Comment C  "
			+ "ON C.article MEMBER OF S.Articles "
			+ "WHERE  C.user.idUser = :userId "
			+ "Group By S.idUser  "
			+ "Order By SIZE(S.Articles) DESC")
			List<Object[]> SortByComments(@Param("userId") Long userId);
}
