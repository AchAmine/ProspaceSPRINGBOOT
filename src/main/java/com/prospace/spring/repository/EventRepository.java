package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.EventDt;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{


	
	@Query(value="SELECT COUNT(*) FROM `event_participants` WHERE events_participation_id_event= :id",nativeQuery = true)
	int countusers(@Param("id")Long id);


	@Query(value="SELECT title,profit FROM `event` ORDER BY profit DESC LIMIT 1",nativeQuery = true)
	EventDt affichermeilleurprofitevent();
}
