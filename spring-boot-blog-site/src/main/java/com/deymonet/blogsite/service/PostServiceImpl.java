package com.deymonet.blogsite.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deymonet.blogsite.dao.PostRepository;
import com.deymonet.blogsite.entity.Post;

/**
 * Implements the {@link PostService} and uses the {@link PostRepository} to customize functionality to the methods.
 * <p>
 * 
 * @author Dasia Melvin
 * @see com.deymonet.blogsite.service.PostService
 */
@Service
public class PostServiceImpl implements PostService {
	/** See {@link PostRepository} */
	private final PostRepository postRepository;
	
	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	/**
	 * Creates a new post and stores the information through the repository into the database
	 * <p>
	 * 
	 * Creating a brand new post already has information that the user doesn't need to add in.
	 * <ul>
	 * <li>{@code postId} is generated through the {@link UUID}
	 * <li>{@code published} is generated through the {@link Date} (can only be used once)
	 * <li>{@code lastUpdated} is generated through the {@link Date}, but can be generated again if the post got updated.
	 * 
	 * @param post - Takes the body of the post and adds in data by the user.
	 * @return Saves the information to the database.
	 */
	@Override
	public Post create(Post post) {
		// Creating a new Post should generate its own unique ID
		String uniquePostId = generatePostId();
		post.setPostId(uniquePostId);

		// Dates will be automatically set and placed in database
		post.setPublished(new Date());
		post.setLastUpdated(new Date());
		
		return this.postRepository.save(post);
	}
	
	/**
	 * Updates the information of an already existing post in the database.
	 * <p>
	 * 
	 * This method will look up a post with the given ID and will allow the user to
	 * change any existing information (possible for {@code title} and {@code content} only).
	 * 
	 * @param id - The Post ID that's auto incremented from the database.
	 * @param post - The entity body being updated.
	 * @return Updated information stored into the backend through the repository.
	 */
	@Override
	public Post updatePost(Long id, Post post) {
		// Update post ONLY IF the post currently exists
		// Find it by the ID. Not Post ID.
		Post existingPost = postRepository.findById(id).get();

		// Get current post information ...
		// begin populating fields that can be updated
		existingPost.setLastUpdated(new Date());
		existingPost.setContent(post.getContent());
		existingPost.setTitle(post.getTitle());
		

		return this.postRepository.save(existingPost);
	}


	/**
	 * Deletes a post by its ID (if the post and ID exists in the database)
	 * 
	 * @param id - The Post ID of that's auto incremented from the database.
	 */
	@Override
	public void deletePost(Long id) {
		// Delete post if it exists in the database
		postRepository.deleteById(id);
	}

	/**
	 * Randomly creates a new unique ID for any new posts created and stored in the database.
	 * 
	 * @return Generates a unique ID for {@code postID};
	 */
	// Generate a unique ID for Post ID
	public String generatePostId() {
		return UUID.randomUUID().toString();
	}
}
