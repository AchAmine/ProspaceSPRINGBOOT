package com.prospace.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Section;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.SectionRepository;
import com.prospace.spring.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceSection implements IServiceSection {
	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	IServicePost_Comment post_commentService;

	public static final String ACCOUNT_SID = "AC1aec12b393e64fc45f33425019c6998c";
	public static final String AUTH_TOKEN = "98f14e4b8f40bd6ac0b4e5b5d8dada88";
	String message;

	public void send() {
		List<User> userList=userRepository.findAll();
		for(User u :userList) {
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message msg = Message
				
				.creator(new com.twilio.type.PhoneNumber("+216"+u.getPhoneNumber()),
						new com.twilio.type.PhoneNumber("+19856148402"), "un nouveau sujet est ouvert !!")
				.create();
		// System.out.println("un nouveau commentaire a été ajouté");
		System.out.println(msg.getSid());
	}}

	@Override
	public List<Section> retrieveAllSections() {
		// TODO Auto-generated method stub
		return (List<Section>) sectionRepository.findAll();
	}

	@Override
	public Section retrieveSection(Long id) {
		Section section = sectionRepository.findById(id).orElse(null);
		log.info("!!!!!!!!!!!!!!!!!" + section.getIdSection());
		return section;
	}

	

	@Override
	public Section addSection(Section s, Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElse(null);
		String str = post_commentService.GetCensoredText(s.getDescription());
		String str1 = post_commentService.GetCensoredText(s.getName());
		s.setDescription(str);
		s.setName(str1);
		s.setUser(user);
		send();
		return sectionRepository.save(s);
	}

	@Override
	public Section updateSection(Section s) {
		// TODO Auto-generated method stub
		String str = post_commentService.GetCensoredText(s.getDescription());
		String str1 = post_commentService.GetCensoredText(s.getName());
		s.setDescription(str);
		s.setName(str1);
		return sectionRepository.save(s);
	}

	@Override
	public void deleteSection(Long id) {
		sectionRepository.deleteById(id);
	}

	@Override
	public List<Section> findByName(String name) {
		return (List<Section>) sectionRepository.findByName(name);
	}

//	@Override
//	public List<Section> findLikedSections() {
//		// TODO Auto-generated method stub
//		List<Section> section = sectionRepository.findLikedSections();
//		return section;
//	}
//
//	@Override
//	public List<Section> findDislikedSections() {
//		// TODO Auto-generated method stub
//		List<Section> section = sectionRepository.findDislikedSections();
//		return section;
//	}
}
