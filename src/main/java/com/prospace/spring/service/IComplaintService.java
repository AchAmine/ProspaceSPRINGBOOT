package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Complaint;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.stateComplaint;

public interface IComplaintService {

	public long AjoutComplaint(Complaint complaint);

	public List<Complaint> GetAllComplaints();

	public long UpdateComplaint(Complaint complaint);

	public void DeleteComplaint(long id);
	
	public User FindUserById (long id );

	public Complaint FindComplaintById (long id);

	public List<Complaint> FindAllComplaintByState(stateComplaint stateComplaint);

}