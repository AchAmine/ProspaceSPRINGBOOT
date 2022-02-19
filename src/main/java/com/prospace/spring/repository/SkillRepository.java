package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{

}
