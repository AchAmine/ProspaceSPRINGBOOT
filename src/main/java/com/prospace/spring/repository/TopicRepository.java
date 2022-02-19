package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

}
