package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long>{

}
