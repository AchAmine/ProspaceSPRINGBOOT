package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

}
