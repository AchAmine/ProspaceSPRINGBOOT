package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Activity;
import com.prospace.spring.service.ServiceActivity;



@RestController
@RequestMapping("/Activity")
public class ActivityController {
	
	@Autowired
	ServiceActivity activityService;
	
	@PostMapping("/add-Activity")
	public Activity addActivity(@RequestBody Activity a) {

	return activityService.addActivity(a);
	}
	
	@DeleteMapping("/remove-activity/{activity-id}")
	public void removeComment(@PathVariable("activity-id") Long idActivity) {
		activityService.deleteActivity(idActivity);
	}

	@PutMapping("/modify-activity")
	public Activity modifyActivity(@RequestBody Activity activity) {
		return activityService.updateActivity(activity);
	}
	
	@GetMapping("/retrieve-activity/{activity-id}")
	public Activity retrieveActivity(@PathVariable("activity-id") Long idActivity) {
		return activityService.retrieveActivity(idActivity);
	}
	@GetMapping("/retrieve-activities")
	public List<Activity> retrieveallActivity() {
		return activityService.retrieveAllActivities();
	}
}
