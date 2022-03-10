package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Complaint;
import com.prospace.spring.entity.stateComplaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{
	
	@Query ("Select c from Complaint c where state =:stateComplaint")
	public List<Complaint> FindAllComplaintByState (@Param("stateComplaint") stateComplaint stateComplaint ) ;
}
