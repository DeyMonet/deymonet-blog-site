package com.deymonet.blogsite.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

import com.deymonet.blogsite.dto.PostDTO;
import com.deymonet.blogsite.entity.Post;
import com.deymonet.blogsite.service.PostService;

/**
 * This class works as the REST controller for the {@link com.deymonet.blogsite.entity.Post} entity class
 * and provides CRUD methods.
 * 
 * @author DeyMonet
 */
@RestController
@RequestMapping("/api/blog_v1/blog")
public class BlogController {
	/** See {@link com.deymonet.blogsite.PersonalBlogSiteApplication} */
	@Autowired
	private ModelMapper modelMapper;
	
	/** See {@link com.deymonet.blogsite.service.PostService} */
	private PostService postService;

	/**
	 * @param postService - Works as an dependency injector for the controller constructor
	 * @see com.deymonet.blogsite.service.PostService
	 */
	@Autowired
	public BlogController(PostService postService) {
		this.postService = postService;
	}

	
	/**
	 * Creates a new post with PostDTO body through HTTP POST request.
	 * 
	 * @param postDto - Used as a request body to send and store the information as a JSON body
	 * @return JSON body of {@link PostDTO} with the populated information provided into the database
	 * @throws SQLIntegrityConstraintViolationException If the required values don't have any information provided. Shows up as {@code null}
	 * @throws HttpMessageNotReadableException If there was a parsing error from the model. Normally from the request body not being in JSON format
	 * 
	 * @see com.deymonet.blogsite.dto.PostDTO
	 */
	// Adding in a POST method in order to create new posts
	@PostMapping(value="/create",
	        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
	        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	)
	public PostDTO createNewPost(@RequestBody PostDTO postDto) {
		Post postReq = modelMapper.map(postDto, Post.class);
		Post post = postService.create(postReq);
		PostDTO postRes = modelMapper.map(post, PostDTO.class);
		
		return postRes;
	}

	/**
	 * Updates an existing post through HTTP PUT request.
	 * 
	 * <p>
	 * This will get the post with the ID (if it exists) and will be able to change certain information.
	 * Everything else besides the {@code title}, {@code content}, and {@code lastUpdated} will not be changed.
	 * 
	 * @param id - Finds the ID that corresponds to the 
	 * @param postDto - Used as a request body to update existing information to the database.
	 * @return Updated information using a JSON body.
	 * 
	 * @see com.deymonet.blogsite.dto.PostDTO
	 */
	// Adding in a PUT method in order to change posts
	// CORS needed for PUT and DELETE method as they were not communicating correctly through both the frontend and backend ...
	@CrossOrigin(origins="${allowed.origins}") 
	@PutMapping("/update/{id}")
	public PostDTO updateExistingPost(@PathVariable("id") Long id, @RequestBody PostDTO postDto) {
		Post postReq = modelMapper.map(postDto, Post.class);
		Post post = postService.updatePost(id, postReq);
		PostDTO postRes = modelMapper.map(post, PostDTO.class);

		return postRes;
	}

	/**
	 * Deletes an existing post through HTTP DELETE request
	 * 
	 * 
	 * @param id - Looks for the ID in the database and deletes the row
	 */
	// Adding in a DELETE method in order to delete posts
	@CrossOrigin(origins="${allowed.origins}")
	@DeleteMapping("/delete/{id}")
	public void deletePost(@PathVariable("id") Long id) {
		postService.deletePost(id);
	}
}
