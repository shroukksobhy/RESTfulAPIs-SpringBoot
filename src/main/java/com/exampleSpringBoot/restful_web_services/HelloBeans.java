package com.exampleSpringBoot.restful_web_services;

public class HelloBeans {
	private String message;

	public HelloBeans(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloBeans [message=" + message + "]";
	}
	
	

}
