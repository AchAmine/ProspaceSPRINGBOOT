package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Complaint;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.stateComplaint;
import com.prospace.spring.repository.ComplaintRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ComplaintService implements IComplaintService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	ComplaintRepository complaintRepository;

	public long AjoutComplaint(Complaint complaint) {

		complaintRepository.save(complaint);

		return complaint.getIdComplaint();
	}

	public List<Complaint> GetAllComplaints() {

		return complaintRepository.findAll();
	}

	public Complaint FindComplaintById(long id) {
		
		return complaintRepository.findById(id).get();
	}

	public long UpdateComplaint(Complaint complaint) {
		complaintRepository.save(complaint);
		return complaint.getIdComplaint();
	}

	public void DeleteComplaint(long id) {
		complaintRepository.deleteById(id);

	}

	public User FindUserById(long id) {

		return userRepository.findById(id).get();

	}

	public void AffectUserToComplaint(long idC, long idUser) {
		Complaint c = FindComplaintById(idC);
		User u = FindUserById(idUser);
		c.setUser(u);
		UpdateComplaint(c);

	}

	public List<Complaint> FindAllComplaintByState (stateComplaint stateComplaint){
		return (List<Complaint>) complaintRepository.FindAllComplaintByState(stateComplaint);
		
	}
}
