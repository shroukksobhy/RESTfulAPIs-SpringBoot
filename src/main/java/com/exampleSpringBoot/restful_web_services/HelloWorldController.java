package com.exampleSpringBoot.restful_web_services;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	

	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
	@GetMapping("/hello-world")
	public String helloWorld1() {
		return "hello roka in spring boot";
	}
	
	@GetMapping("/hello-world-beans")
	public HelloBeans helloWorld() {
		return new HelloBeans("rokie");
	}

	@GetMapping("/hello-world/path-v/{name}")
	public HelloBeans helloWorldPathV(@PathVariable String name) {
		return new HelloBeans("Hello "+ name);
	}
	
	@GetMapping("/hello-world-languages")	
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("gm.message", null, "Defauld Message", locale);
		
		
	}

}
