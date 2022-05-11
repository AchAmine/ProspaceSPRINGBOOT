package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Rating;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.Offer;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
    List<Rating> findByOffer(Offer offer);
    Rating findByUserAndOffer(User user , Offer offer);
    
    
	@Query(value = "SELECT AVG(rate) FROM rating "
		+ "WHERE rating.offer_id_offer= :offerid "
	,
			nativeQuery = true)
	float AVGOffer(@Param("offerid") Long offerid);
    
    
}
