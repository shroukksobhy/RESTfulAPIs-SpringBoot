package com.exampleSpringBoot.restful_web_services.users;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exampleSpringBoot.restful_web_services.UserDAOservice;

@RestController
public class UserResouce {

	private UserDAOservice service;
	
	
	public UserResouce(UserDAOservice service) {
		super();
		this.service = service;
	}

	//GET /users
	@GetMapping("/users")
	public List<Users> retrieveAllUsers() {
		return service.findAll();
	}
	
	
	//GET /user/{ID}
	@GetMapping("/users/{id}")
	public Users showUser(@PathVariable int id) {
		//return service.findOne(id);
		
		Users user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		return user;
		
	}
	
	//POST /user/{ID}
	@PostMapping("/users")
	public ResponseEntity<Users> storeUser(@RequestBody Users user) {
		// Save the user
		Users savedUser  = service.save(user);
		//Get user ID url
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getID())
						.toUri();
		//Return the response
		return ResponseEntity.created(location ).build();
		
	}
	
	
}
