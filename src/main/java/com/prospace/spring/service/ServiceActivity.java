package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Activity;

import com.prospace.spring.repository.ActivityRepository;


@Service
public class ServiceActivity implements IServiceActivity {

	@Autowired
	ActivityRepository activityRepository;
	
	@Override
	public Activity addActivity(Activity A) {
		return activityRepository.save(A);
	}

	@Override
	public void deleteActivity(Long idActivity) {
		Activity activity = activityRepository.findById(idActivity).orElse(null);
		activityRepository.delete(activity);
		
	}

	@Override
	public Activity updateActivity(Activity a) {
		return activityRepository.save(a);
	}

	@Override
	public Activity retrieveActivity(Long idActivity) {
		return activityRepository.findById(idActivity).orElse(null);
	}

	@Override
	public List<Activity> retrieveAllActivities() {
		
		return activityRepository.findAll();
	}

}
