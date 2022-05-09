package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.Quizz;
import com.prospace.spring.service.IServicePartner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Partner management")
@RequestMapping("/Partner")
public class PartnerRestController {
	
	@Autowired
	IServicePartner servicePartner;

	 @ApiOperation(value = "Add Partner")
		@PostMapping("/add-partner/{User-id}")
		public Partner addPartner(@RequestBody Partner p,@PathVariable("User-id")Long UserId) {
		

		 return servicePartner.AddPartner(p,UserId);
		}

	 
	

	
}
