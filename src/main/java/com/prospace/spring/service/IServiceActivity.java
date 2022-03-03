package com.prospace.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Activity;


@Service

public interface IServiceActivity {
	Activity addActivity(Activity A);
	void deleteActivity(Long idActivity);
	Activity updateActivity(Activity a);
	Activity retrieveActivity(Long idActivity);
	List<Activity> retrieveAllActivities();

}
