package com.deymonet.blogsite.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity class used to store information and to allow visitors to log into the application.
 * Using the body of this class, the user is able use a login form to populate it with
 * their information.
 * 
 * 
 * @author Dasia Melvin
 * @see com.deymonet.blogsite.entity.Post
 * @see com.deymonet.blogsite.entity.Topic
 */
@Entity
@Table(name="blog_users") // Changed table names from MySQL accordance to PostrgeSQL
@Getter
@Setter
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	/** Auto incremental identity. Only for lookups through the database. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	/**
	 * Unique auto-generated ID for every new user added into the database.
	 */
	@Column(name="user_id")
	private String userId;
	
	/** Unique user-created name. There cannot be a duplicate of another name if it exists within the database. */
	@Column(name="username")
	private String username;
	
	/** The user's first name. */
	@Column(name="first_name")
	private String firstName;
	
	/** The user's last name. */
	@Column(name="last_name")
	private String lastName;
	
	/** 
	 * The user's email address. There cannot be duplicates existing in the database.
	 * <p>
	 * This must also be a valid email address.
	 */
	@Column(name="email")
	private String email;
	
	/** The user's password. */
	@Column(name="password")
	private String password;
	
}
