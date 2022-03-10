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

import com.prospace.spring.entity.Badge;
import com.prospace.spring.service.BadgeService;

@RestController
public class BadgeController {

	@Autowired
	BadgeService badgeService;

	@GetMapping("/getAllBAdges")
	@ResponseBody
	public List<Badge> GetAllBadges() {

		return badgeService.GetAllBAdges();
	}

	@PostMapping("/ajoutBadge")
	@ResponseBody
	public long AjoutBadge(@RequestBody Badge badge) {
		return badgeService.AjoutBadge(badge);

	}

	@PutMapping("/updateBadge")
	@ResponseBody
	public long UpdateBadge(@RequestBody Badge badge) {
		return badgeService.UpdateBadge(badge);
	}

	@DeleteMapping("/deleteBadge/{id}")
	@ResponseBody
	public void DeleteBadge(@PathVariable long id) {
		badgeService.DeleteBadge(id);
	}

	@GetMapping("/FindBadgeById/{id}")
	@ResponseBody
	public Badge FindBadgeById(@PathVariable long id) {
		return badgeService.FindBadgeById(id);

	}

	@PutMapping("/AffectBadgeToUser/{idbadge}-{idUser}")
	@ResponseBody
	public void AffectBadgeToUser(@PathVariable("idbadge") long idbadge, @PathVariable("idUser") long idUser) {
		badgeService.AffectBadgeToUser(idbadge, idUser);
	}

	@PutMapping("/CalculScoreEventParticipationBadge/{idUser}")
	@ResponseBody
	public void CalculScoreEventParticipationBadge(@PathVariable ("idUser" ) long idUser) {
		badgeService.CalculScoreEventParticipationBadge(idUser);
	}
}
