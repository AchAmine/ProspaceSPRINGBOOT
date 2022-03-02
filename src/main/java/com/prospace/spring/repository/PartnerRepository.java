package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.User;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
	
}
