package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Section;
import com.prospace.spring.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	List<Topic> findBySection(Section section);

	
	
	
	@Query(value = "SELECT T FROM Topic T INNER JOIN Post_Reaction R ON R.topic.idTopic=T.idTopic WHERE R.type='Like' ")
	List<Topic> findTopicLike();

	@Query(value = "SELECT T FROM Topic T INNER JOIN Post_Reaction R ON R.topic.idTopic=T.idTopic WHERE R.type='Dislike' ")
	List<Topic> findTopicDislikes();
}
