package com.deymonet.blogsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * This class works as an entity to display information for each post that falls under
 * each topic name. 
 * 
 * @author Dasia Melvin
 * @see com.deymonet.blogsite.entity.Post
 * @see com.deymonet.blogsite.entity.User
 */
@Entity
@Table(name="blog_topics") // Changed table names from MySQL accordance to PostrgeSQL
@Getter
@Setter
public class Topic {
	/** Auto incremental identity. Only for lookups through the database. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/** Unique auto-generated ID for every topic. */
	@Column(name="topic_id")
	private String topicId;
	
	/** 
	 * The topic's name. 
	 * <p>
	 * Used to categorize topics with their given topic tags.
	 * @see com.deymonet.blogsite.entity.Post
	 * */
	@Column(name="topic_name")
	private String topicName;
	
}
