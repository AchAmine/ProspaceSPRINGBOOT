package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Event_Comment;

@Repository
public interface Event_CommentRepository extends JpaRepository<Event_Comment, Long>{

}
