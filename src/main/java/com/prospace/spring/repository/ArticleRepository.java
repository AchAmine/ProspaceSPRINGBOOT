package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Article;
import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.ReactionType;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

	List<Article> findByUser(User user);
	
	/*@Query(value = "SELECT A.user.idUser , A.idArticle "
			+ "FROM Article A "
			+ "INNER JOIN Reaction R "
			+ "ON R.article.idArticle = A.idArticle "
			+ "WHERE (R.type = com.prospace.spring.entity.ReactionType.Love "
			+ "OR R.type =  com.prospace.spring.entity.ReactionType.Funny) And   R.user.idUser = :userId ")
			List<Object[]> SortByReaction(@Param("userId") Long userId );*/
	
				@Query(value = "SELECT A.user.idUser , A.idArticle "
						+ "FROM Article A "
						+ "INNER JOIN Reaction R "
						+ "ON R.article.idArticle = A.idArticle " 
						+ "WHERE (R.type = \'Love\' "
						+ "OR R.type =  \'Funny\') And R.user.idUser = :userId ")
						List<Object[]> SortByReaction(@Param("userId") Long userId );
				
						
				@Query(value = "SELECT A.user.idUser , A.idArticle "
						+ "FROM Article A "
						+ "INNER JOIN Article_Comment C  "
						+ "ON C.article.idArticle = A.idArticle "
						+ "WHERE  C.user.idUser = :userId ")
						List<Object[]> SortByComments(@Param("userId") Long userId);
			
			
				@Query(value = "SELECT A FROM Article A "
				 		+ "LEFT JOIN User U ON A.user.idUser = U.idUser "
				 		+ "WHERE U in :users "
				 		+ "ORDER BY A.createdAt DESC") 
						List<Article> FollowingArticles(@Param("users") List<User> users);
			
	
}
