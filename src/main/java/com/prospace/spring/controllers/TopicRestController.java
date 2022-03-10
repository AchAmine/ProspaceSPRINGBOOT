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

import com.prospace.spring.entity.Topic;
import com.prospace.spring.service.IServiceTopic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Topic management")
@RequestMapping("/Topic")
public class TopicRestController {
	@Autowired
	IServiceTopic topicService;

	@ApiOperation(value = "show all topics")
	@GetMapping("/retrieve-all-topics")
	public List<Topic> getSections() {
		List<Topic> listTopics = topicService.retrieveAllTopics();
		return listTopics;
	}

	@ApiOperation(value = "show one topic")
	@GetMapping("/retrieve-topic/{topic-id}")
	public Topic retrieveSection(@PathVariable("topic-id") Long id) {

		return topicService.RetrieveTopic(id);
	}

	@ApiOperation(value = "Add topic")
	@PostMapping("/add-topic/{user-id}/{section-id}")
	public Topic save(@RequestBody Topic t, @PathVariable("user-id") Long userId,
			@PathVariable("section-id") Long sectionId) {

		return topicService.addTopic(t, userId, sectionId);
	}

	@ApiOperation(value = "delete topic")
	@DeleteMapping("/remove-topic/{topic-id}")
	public void delete(@PathVariable("topic-id") Long topicId) {
		topicService.deleteTopic(topicId);
	}

	@ApiOperation(value = "Update topic")

	@PutMapping("/modify-topic")
	public Topic update(@RequestBody Topic topic) {
		return topicService.updateTopic(topic);
	}

	@ApiOperation(value = "Show Liked Topics  ")
	@GetMapping("/find-byLike")
	public List<Topic> findTopicsByReactions() {
		return topicService.findTopicLike();
	}

	@ApiOperation(value = "Show disliked Topics  ")
	@GetMapping("/find-byDislike")
	public List<Topic> findDislikedTopics() {
		return topicService.findTopicDislike();
	}
}
