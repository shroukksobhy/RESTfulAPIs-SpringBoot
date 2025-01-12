package com.exampleSpringBoot.restful_web_services.users;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exampleSpringBoot.restful_web_services.UserDAOservice;
import com.exampleSpringBoot.restful_web_services.exception.UserNotFoundException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class UserResouce {

	private UserDAOservice service;
	private Users user;
	
	
	public UserResouce(UserDAOservice service) {
		super();
		this.service = service;
	}

	//GET /users
	@GetMapping("/users")
	public List<Users> retrieveAllUsers() {
		return service.findAll();
	}
	
	
	//GET /users/{ID}
	@GetMapping("/users/{id}")
	public EntityModel<Users> showUser(@PathVariable int id) {
		//return service.findOne(id);
		
		Users user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		//return user;
	
		EntityModel<Users> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
		
	}
	
	//POST /user/{ID}
	@PostMapping("/users")
	public ResponseEntity<Users> storeUser(@Valid @RequestBody Users user) {
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
	
	//DELETE  /user/{id}
	@DeleteMapping("/users/{id}")
	public void  deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		
	}
	
	
	
}
