package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Complaint;
import com.prospace.spring.entity.User;
import com.prospace.spring.entity.stateComplaint;
import com.prospace.spring.service.ComplaintService;

@RestController
public class CompalintController {

	@Autowired
	ComplaintService complaintService;

	@PostMapping("/ajoutComplaint")
	@ResponseBody
	public long AjoutComplaint(@RequestBody Complaint complaint) {
		return complaintService.AjoutComplaint(complaint);

	}

	@GetMapping("/getAllComplaints")
	@ResponseBody
	public List<Complaint> GetAllComplaints() {

		return complaintService.GetAllComplaints();
	}
	
	@GetMapping("/findComplaintbyId/{id}")
	@ResponseBody
	public Complaint FindComplaintById(@PathVariable long id) {

		return complaintService.FindComplaintById(id);
	}
	

	@PutMapping("/updateComplaint")
	@ResponseBody
	public long UpdateComplaint(@RequestBody Complaint complaint) {
		return complaintService.UpdateComplaint(complaint);
	}

	@DeleteMapping("/deleteComplaint/{id}")
	@ResponseBody
	public void DeleteComplaint(@PathVariable("id") long id) {
		complaintService.DeleteComplaint(id);
	}
	@GetMapping("/FindUserById/{id}")
	@ResponseBody
	public User FindUserById (@PathVariable long id){
		return complaintService.FindUserById(id);
		
	}
	
	@PutMapping("/AffectUserToComplaint/{idC}-{idUser}")
	@ResponseBody
	public void AffectUserToComplaint(@PathVariable("idC") long idC,@PathVariable("idUser") long idUser){
		complaintService.AffectUserToComplaint(idC,idUser);	
	}

	@GetMapping("/FindAllComplaintByStateRejected")
	@ResponseBody
	public List<Complaint> FindAllComplaintByStateByRejected() {

		return complaintService.FindAllComplaintByState(stateComplaint.rejected);
	}

	@GetMapping("/FindAllComplaintByStateWaiting")
	@ResponseBody
	public int FindAllComplaintByStateByWaiting() {

		return complaintService.FindAllComplaintByState(stateComplaint.waiting).size();
	}

	@GetMapping("/FindAllComplaintByStateProcessed")
	@ResponseBody
	public List<Complaint> FindAllComplaintByStateByProcessed() {

		return complaintService.FindAllComplaintByState(stateComplaint.processed);
	}
}
