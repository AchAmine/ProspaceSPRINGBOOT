package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Section;
import com.prospace.spring.entity.Topic;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.SectionRepository;
import com.prospace.spring.repository.TopicRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class ServiceTopic implements IServiceTopic {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SectionRepository sectionRepository;

	@Override
	public List<Topic> retrieveAllTopics() {
		// TODO Auto-generated method stub
		return (List<Topic>) topicRepository.findAll();
	}

	@Override
	public Topic RetrieveTopic(Long id) {
		// TODO Auto-generated method stub
		return topicRepository.findById(id).orElse(null);
	}

	@Override
	public Topic addTopic(Topic t, Long userId, Long sectionId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElse(null);
		Section section = sectionRepository.findById(sectionId).orElse(null);
		t.setSection(section);
		t.setUser(user);
		return topicRepository.save(t);
	}

	@Override
	public void deleteTopic(Long id) {
		// TODO Auto-generated method stub
		topicRepository.deleteById(id);

	}

	@Override
	public Topic updateTopic(Topic to) {
		// TODO Auto-generated method stub
		return topicRepository.save(to);
	}

}
