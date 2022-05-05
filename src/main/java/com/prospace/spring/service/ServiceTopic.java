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
		String str = post_commentService.GetCensoredText(to.getTitle());
		String str1 = post_commentService.GetCensoredText(to.getDescription());
		to.setTitle(str);
		to.setDescription(str1);
		return topicRepository.save(to);
	}

	@Override
	public List<Topic> findTopicLike() {
		// TODO Auto-generated method stub
		List<Topic> topic = topicRepository.findTopicLike();
		return topic;
	}

	@Override
	public List<Topic> findTopicDislike() {
		// TODO Auto-generated method stub
		List<Topic> topic = topicRepository.findTopicDislikes();
		return topic;
	}

}
