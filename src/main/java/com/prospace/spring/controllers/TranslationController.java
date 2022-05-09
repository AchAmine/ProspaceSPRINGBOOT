package com.prospace.spring.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prospace.spring.service.Translator;

import io.swagger.annotations.ApiOperation;

@RestController
@ApiOperation("Translate")
@RequestMapping(value = "/api")
public class TranslationController {

	@GetMapping()
	public String getMessage(@RequestParam("msg") String message) {
		return Translator.toLocale(message);
	}

/*
	
	@GetMapping("/translate")
	  public String translate(Model model) {
	    model.addAttribute("userInput", new UserInput());
	    
	    return "translation";
	  }
	
	@PostMapping("/translate")
	public String translate(@ModelAttribute UserInput userInput, Model model) {
		
		TranslatedOutput output = t.translateInput(userInput);
		
		if(null != output && output.getSentences().size() > 0) {
			
			Sentences sentence = output.getSentences().get(0);
			model.addAttribute("sentence", sentence);
		
		} else { 
		
			model.addAttribute("sentence", null);
		}
		
		return "result";
	}*/
	}
