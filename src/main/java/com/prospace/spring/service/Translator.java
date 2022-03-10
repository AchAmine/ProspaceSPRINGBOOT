package com.prospace.spring.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator {

   private static  ResourceBundleMessageSource messageSource;

   @Autowired
   Translator(ResourceBundleMessageSource messageSource) {
      Translator.messageSource = messageSource;
   }

   public static  String toLocale(String msg) {
      Locale locale = LocaleContextHolder.getLocale();
      return messageSource.getMessage(msg, null, locale);
   }
   

	/*
	public TranslatedOutput translateInput(UserInput userInput) {

		String googleTranslationUrl = "https://translate.google.com/translate_a/single?client=at&dt=t&dt=ld&dt=qca&dt=rm&dt=bd&dj=1&hl=%25s&ie=UTF-8&oe=UTF-8&inputm=2&otf=2&iid=1dd3b944-fa62-4b55-b330-74909a99969e&";

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
		headers.set("User-Agent", "AndroidTranslate/5.3.0.RC02.130475354-53000263 5.1 phone TRANSLATE_OPM5_TEST_1");

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("sl", userInput.getSourceLang());
		map.add("tl", userInput.getTargetLang());
		map.add("q", userInput.getContent());

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<TranslatedOutput> response = restTemplate().exchange(googleTranslationUrl, HttpMethod.POST, entity,
				TranslatedOutput.class);

		return response.getBody();
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	*/
	
}