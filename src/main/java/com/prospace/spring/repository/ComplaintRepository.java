package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{

}
