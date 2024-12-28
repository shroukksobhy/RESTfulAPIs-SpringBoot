package com.exampleSpringBoot.restful_web_services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
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

}
