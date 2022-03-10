package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
