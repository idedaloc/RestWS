package com.example.demo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.expetions.UserNotFoundException;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	
	
	@GetMapping("/jpa/users")
	public List<User> retrieveJpaAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveJpaAllPostsUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		return user.get().getPosts();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveJpaUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);

		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}

		Resource<User> res = new Resource<User>(user.get());

		ControllerLinkBuilder lingTo = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		res.add(lingTo.withRel("all-users"));

		return res;

	}
	

	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-"+id);
		}

		Resource<User> res = new Resource<User>(user);

		ControllerLinkBuilder lingTo = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		res.add(lingTo.withRel("all-users"));

		return res;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);

		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createJPAUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<User> createJPAUser(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		User user = optionalUser.get();
		
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/user/{id}")
	public void deleteJPAUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}



}
