package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long>{

}
