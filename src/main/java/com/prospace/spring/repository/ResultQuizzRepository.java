package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.ResultQuizz;

@Repository

public interface ResultQuizzRepository extends JpaRepository<ResultQuizz,Long> {

}
