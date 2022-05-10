package com.prospace.spring.service;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Section;
import com.prospace.spring.entity.Topic;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.SectionRepository;
import com.prospace.spring.repository.TopicRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceTopic implements IServiceTopic {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	IServicePost_Comment post_commentService;

	@Override
	public List<Topic> retrieveAllTopics(Long sectionId) {
		// TODO Auto-generated method stub
		//return (List<Topic>) topicRepository.findById(sectionId);
		Section section = sectionRepository.findById(sectionId).orElse(null);

		List<Topic> topics =  topicRepository.findBySection(section);
return topics;
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
		String str = post_commentService.GetCensoredText(t.getTitle());
		String str1 = post_commentService.GetCensoredText(t.getDescription());
		t.setTitle(str);
		t.setDescription(str1);
		Date date = new Date(System.currentTimeMillis());
		t.setCreationDate(date);
		return topicRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteTopic(Long idTopic) {
		// TODO Auto-generated method stub
		//Topic topic = topicRepository.findById(id).orElse(null);

		topicRepository.deleteTopic(idTopic);

	}

	@Override
	public Topic updateTopic(Topic to) {
		Topic topic = topicRepository.findById(to.getIdTopic()).orElse(null);

		// TODO Auto-generated method stub
		String str = post_commentService.GetCensoredText(to.getTitle());
		String str1 = post_commentService.GetCensoredText(to.getDescription());
		to.setTitle(str);
		to.setDescription(str1);
		Date date = new Date(System.currentTimeMillis());
		to.setCreationDate(date);
		to.setSection(topic.getSection());
		to.setUser(topic.getUser());
		return topicRepository.save(to);
	} 

//	@Override
//	public List<Topic> findTopicLike() {
//		// TODO Auto-generated method stub
//		List<Topic> topic = topicRepository.findTopicLike();
//		return topic;
//	}
//
//	@Override
//	public List<Topic> findTopicDislike() {
//		// TODO Auto-generated method stub
//		List<Topic> topic = topicRepository.findTopicDislikes();
//		return topic;
//	}
//	
	
	
	
	
	@Override
	public Topic viewIncrement(Topic topic) {
		Topic topic1 = topicRepository.findById(topic.getIdTopic()).orElse(null);
		log.info("------------------topic Before inc"+topic1);
		log.info("-----------------Views Before inc"+topic1.getViews());
		topic1.setViews(topic1.getViews() + 1); 
		log.info("-----------------Views After inc"+topic1.getViews());
		return topicRepository.save(topic1);
	}
}
