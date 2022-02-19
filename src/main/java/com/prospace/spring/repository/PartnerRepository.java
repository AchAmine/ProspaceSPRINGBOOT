package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>{

}
