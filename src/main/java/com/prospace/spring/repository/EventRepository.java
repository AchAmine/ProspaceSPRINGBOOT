package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
