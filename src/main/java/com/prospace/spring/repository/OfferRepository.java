package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{

}
