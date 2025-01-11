package com.exampleSpringBoot.restful_web_services.jpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exampleSpringBoot.restful_web_services.exception.UserNotFoundException;
import com.exampleSpringBoot.restful_web_services.users.Post;
import com.exampleSpringBoot.restful_web_services.users.Users;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class PostResourceJPA {
	private UserRepository user_repository;
	private PostRepository post_repository;
	
	public PostResourceJPA(UserRepository user_repository, PostRepository post_repository) {
		super();
		this.user_repository = user_repository;
		this.post_repository = post_repository;
	}

	//Create posts for users  /user/{id}/posts
	@PostMapping("/JPA/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<Users> userOptional  = user_repository.findById(id);
		if(userOptional.isEmpty())
			throw new UserNotFoundException("User not found with id: " + id);
		
	    Users user = userOptional.get();
		post.setUser(user);
		
		Post saved_post = post_repository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved_post.getID())
				.toUri();
	    return ResponseEntity.created(location).body(saved_post);
 
	}


	
}
