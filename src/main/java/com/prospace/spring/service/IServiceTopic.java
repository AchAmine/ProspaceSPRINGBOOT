package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Topic;

public interface IServiceTopic {
	List<Topic> retrieveAllTopics();

	Topic RetrieveTopic(Long id);

	Topic addTopic(Topic t, Long userId, Long sectionId);

	void deleteTopic(Long id);

	Topic updateTopic(Topic to);
}
