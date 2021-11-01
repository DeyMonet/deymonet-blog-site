package com.deymonet.blogsite.dto;

import java.util.Date;

import lombok.Data;

/**
 * Data Transfer Object that represents the {@link Post} entity.
 * <p>
 * This class is used to send information to the database through the service layer.
 * This also acts as a request body for any CRUD methods being passed in through the parameters.
 * 
 * @author Dasia Melvin
 * @see com.deymonet.blogsite.entity.Post
 * @see com.deymonet.blogsite.service.PostService
 */
@Data
public class PostDTO {
	private Long id;
	private String postId;
	private String title;
	private String content;
	private Date published;
	private Date lastUpdated;
	private String topicTag;
}
