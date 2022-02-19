package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Post_Reaction;

@Repository
public interface Post_ReactionRepository extends JpaRepository<Post_Reaction, Long>{

}
