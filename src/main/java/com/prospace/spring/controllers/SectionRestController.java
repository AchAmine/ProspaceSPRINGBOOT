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

import com.prospace.spring.entity.Section;
import com.prospace.spring.service.IServiceSection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Section management")
@RequestMapping("/Section")
public class SectionRestController {
	@Autowired
	IServiceSection serviceSection;

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

	/*@ApiOperation(value = "show one section by name ")
	@GetMapping("/retrieve-section/{section-name}")
	public List<Section> findByName(@PathVariable("section-name") String name) {

		return serviceSection.getSectionByName(name);
	}*/

	@ApiOperation(value = "Add section")
	@PostMapping("/add-section")
	public Section save(@RequestBody Section s) {

		return serviceSection.addSection(s);
	}

	@ApiOperation(value = "delete section")
	@DeleteMapping("/remove-section/{section-id}")
	public void delete(@PathVariable("section-id") Long sectionId) {
		serviceSection.deleteSection(sectionId);
	}
	@ApiOperation(value = "Update section")

	@PutMapping("/modify-section")
	public Section update(@RequestBody Section section) {
		return serviceSection.updateSection(section);
	}

}
