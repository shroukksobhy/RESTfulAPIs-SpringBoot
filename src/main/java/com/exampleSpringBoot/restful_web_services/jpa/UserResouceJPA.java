package com.exampleSpringBoot.restful_web_services.jpa;

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
import com.exampleSpringBoot.restful_web_services.users.Post;
import com.exampleSpringBoot.restful_web_services.users.Users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class UserResouceJPA {
	
	private UserRepository repository;

	private Users user;
	
	
	public UserResouceJPA(UserRepository userRepo) {
		super();
		this.repository = userRepo;

	}

	//GET /users
	@GetMapping("/JPA/users")
	public List<Users> retrieveAllUsers() {
		return repository.findAll();
	}
	
	
	//GET /users/{ID}
	@GetMapping("/JPA/users/{id}")
	public EntityModel<Users> showUser(@PathVariable int id) {
		//return service.findOne(id);
		
		Optional<Users> user = repository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
	
		
		//return user;
	
		EntityModel<Users> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	//POST /user/{ID}
	@PostMapping("/JPA/users")
	public ResponseEntity<Users> storeUser(@Valid @RequestBody Users user) {
		// Save the user
		Users savedUser  = repository.save(user);
		//Get user ID url
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getID())
						.toUri();
		//Return the response
		return ResponseEntity.created(location ).build();
	}
	
	//DELETE  /user/{id}
	@DeleteMapping("/JPA/users/{id}")
	public void  deleteUser(@PathVariable int id) {
		repository.deleteById(id);
		
	}
	
	//Get Use posts  /user/{id}/posts
	@GetMapping("/JPA/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<Users> user = repository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		List<Post> posts= user.get().getPosts();
		return posts;
	}
	
}
