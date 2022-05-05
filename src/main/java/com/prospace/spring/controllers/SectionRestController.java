package com.prospace.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.entity.Section;
import com.prospace.spring.service.IServicePost_Comment;
import com.prospace.spring.service.IServiceSection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "http://localhost:4200")
 
@RestController
@Api(tags = "Section management")
@RequestMapping("/Section")
public class SectionRestController {
	@Autowired
	IServiceSection serviceSection;
	@Autowired
	IServicePost_Comment post_commentService;

	@ApiOperation(value = "show all sections")
	@GetMapping("/retrieve-all-sections")
	public List<Section> getSections() {
		List<Section> listSections = serviceSection.retrieveAllSections();
		return listSections;
	}

	@ApiOperation(value = "show one section")
	@GetMapping("/retrieve-section/{section-id}")
	public Section retrieveSection(@PathVariable("section-id") Long id) {

		return serviceSection.retrieveSection(id);
	}

	@ApiOperation(value = "show one section by name")
	@GetMapping("/retrieve-one-section/{section-name}")
	public List<Section> findByName(@PathVariable("section-name") String name) {

		List<Section> listSections = serviceSection.findByName(name);
		return listSections;
	}

	@ApiOperation(value = "Add section")
	@PostMapping("/add-section/{user-id}")
	public Section save(@RequestBody Section s, @PathVariable("user-id") Long userId) {
		String str = post_commentService.GetCensoredText(s.getDescription());
		String str1 = post_commentService.GetCensoredText(s.getName());
		s.setDescription(str);
		s.setName(str1);
		return serviceSection.addSection(s, userId);
	}

	@ApiOperation(value = "delete section")
	@DeleteMapping("/remove-section/{section-id}")
	public void delete(@PathVariable("section-id") Long sectionId) {
		serviceSection.deleteSection(sectionId);
	}

	@ApiOperation(value = "Update section")

	@PutMapping("/modify-section")
	public Section update(@RequestBody Section section) {
		String str = post_commentService.GetCensoredText(section.getDescription());
		String str1 = post_commentService.GetCensoredText(section.getName());
		section.setDescription(str);
		section.setName(str1);
		return serviceSection.updateSection(section);
	}

	@ApiOperation(value = "Show Liked Section ")
	@GetMapping("/find-liked")
	public List<Section> findLikedSections() {
		return serviceSection.findLikedSections();
	}

	@ApiOperation(value = "Show Disliked Section ")
	@GetMapping("/find-disliked")
	public List<Section> findDislikedSections() {
		return serviceSection.findDislikedSections();
	}

}
