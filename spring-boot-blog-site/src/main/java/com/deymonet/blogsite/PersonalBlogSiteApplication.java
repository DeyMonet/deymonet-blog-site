package com.deymonet.blogsite;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PersonalBlogSiteApplication {
	/**
	 * Used to help with JSON parsing issue through any information attempting to be sent to the database.
	 * <p>
	 * This is used more for Data Transfer Objects (DTOs) within the service layer.
	 * 
	 * @see org.modelmapper.ModelMapper
	 */
	// Resolved JSON parse issue through FasterXML exception
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PersonalBlogSiteApplication.class, args);
		
	}
}
