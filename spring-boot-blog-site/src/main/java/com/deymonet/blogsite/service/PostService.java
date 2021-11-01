package com.deymonet.blogsite.service;

import com.deymonet.blogsite.entity.Post;

/**
 * Service layer for the {@link Post} entity.
 * <p> 
 * Takes the body of the Post entity and implements custom CRUD repository methods for the controller layer.
 * 
 * @see com.deymonet.blogsite.entity.Post
 * @author Dasia Melvin
 */
public interface PostService {
	/** Creates a new a post */
	public Post create(Post post);
	/** Updates an existing post */
	public Post updatePost(Long id, Post post);
	/** Deletes an existing post */
	public void deletePost(Long id);
}
