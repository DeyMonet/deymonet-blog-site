package com.deymonet.blogsite.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity class to display and store information of the body of a post
 * 
 * @author Dasia Melvin
 * @see com.deymonet.blogsite.entity.User
 * @see com.deymonet.blogsite.entity.Topic
 */
@Entity
@Table(name="blog_posts") // Changed table names from MySQL accordance to PostrgeSQL
@Getter
@Setter
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 
	 * Used to find the ID of each post within the database.
	 * <p>
	 * This auto increments for every new post created.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/** 
	 * Unique auto-generated ID for every new post added into the database
	 */
	@Column(name="post_id")
	private String postId;
	
	/** The title of the post. Cannot be null while creating a new post and updating an existing post. */
	@Column(name="title")
	private String title;
	
	/** The body of the post. Cannot be null while creating a new post and updating an existing post. */
	@Column(name="content")
	private String content;
	
	/** Shows the date when the post was initially created. */
	@Column(name="published")
	private Date published;
	
	/** Gets the current date if the post was updated recently. */
	@Column(name="last_updated")
	private Date lastUpdated;
	
	/** 
	 * Where the post is categorized within the application
	 * <p>
	 * This is paired with {@link com.deymonet.blogsite.entity.Topic} and pointing to the topic name.
	 * This cannot be null while creating a new topic or updating an existing one.
	 */
	@Column(name="topic_tag")
	private String topicTag;
}
