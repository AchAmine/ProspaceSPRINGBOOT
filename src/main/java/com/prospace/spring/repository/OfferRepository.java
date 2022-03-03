package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.User;



@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{
	 
	// @Query("Select o FROM Offer o join o.Ratings r GROUP BY o.idOffer order by sum(r.rate) DESC ")
 @Query("SELECT o FROM Offer o LEFT JOIN o.Ratings r "
 		+ "WHERE o IN :offers "
 		+ "GROUP BY o.idOffer "
 		+ "ORDER BY avg(r.rate) DESC") 
	List<Offer> RatingTri(@Param("offers") List<Offer> offers);
	
	 @Query("SELECT o FROM Offer o LEFT JOIN o.Ratings r "
	 		+ "WHERE current_date BETWEEN o.startsAt AND o.endsAt")
		List<Offer> TodaysOffers();
 

 @Query("Select DISTINCT o.partner FROM Offer o join o.partner u GROUP BY u.idUser  ")
	List<User> PartnersList();
	
}
