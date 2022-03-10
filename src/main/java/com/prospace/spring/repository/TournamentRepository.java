package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Activity;
import com.prospace.spring.entity.TopActiviteInTournament;
import com.prospace.spring.entity.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long>{


	List<Tournament> findByActivity(Activity activity);

	@Query(value="select activity.type ,count(tournament.activity_id_activity) as nbr from activity"
			+ " left join tournament on activity.id_activity=tournament.activity_id_activity "
			+ "GROUP by (activity.type)"
			+ "ORDER by (nbr) DESC "
			+ "limit 1",nativeQuery = true)
	List<TopActiviteInTournament>topacttourn();
}
