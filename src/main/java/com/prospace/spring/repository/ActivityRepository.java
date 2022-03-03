package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Activity;
import com.prospace.spring.entity.Tournament;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{



}
