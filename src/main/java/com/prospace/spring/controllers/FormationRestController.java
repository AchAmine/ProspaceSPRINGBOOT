package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Formation;
import com.prospace.spring.service.ServiceFormation;

@RestController
@RequestMapping("/formation")
public class FormationRestController {
	@Autowired
	ServiceFormation serviceFormation;

	@PostMapping("/addformation/{idu}")
	public void addFormation(@RequestBody Formation f, @PathVariable("idu") Long idu) {
		serviceFormation.addFormation(f, idu);
	}

	@GetMapping("/getformation")
	public List<Formation> retreiveAllFormation() {
		return serviceFormation.getFormations();
	}

	@GetMapping("/getundfor")
	public List<Formation> retreiveAllUndeletedFormation() {
		return serviceFormation.getUndeletedFormation();
	}
	@PutMapping("/updateformation")
	public void updateForamtion(@RequestBody Formation f) {
		serviceFormation.updateFormation(f);
	}
	@PutMapping("/deleteformation/{idf}")
	public void deleteForamtion(@PathVariable("idf") Long idf) {
		serviceFormation.deleteFormation(idf);
	}
	@PutMapping("/participerformation/{idf}/{idu}")
	public void participerForamtion(@PathVariable("idf") Long idf,@PathVariable("idu") Long idu) {
		serviceFormation.participerFormation(idf, idu);
	}
}
