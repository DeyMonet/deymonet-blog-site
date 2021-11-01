package com.deymonet.blogsite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Allows the backend API to communicate to the frontend of the application.
 * 
 * <p>
 * In order for the backend to get information through the frontend,
 * the provided URL must be written within the {@code applications.properties} file
 * and added as a parameter for the {@code @Value} annotation.
 * the
 * 
 * <pre>{@code 
 * 		// This gets the frontend URL
 * 		@Value("${your.custom.property}")
		private String[] allowedOrigins;

		// This gets the endpoint of the API
		@Value("${spring.data.rest.base-path}")
		private String basePath;
 * </pre>
 * 
 * <p>
 * This class implements the {@link WebMvcConfigurer} to get the method required for CORS.
 * Requiring both variables in order to build a path for the backend to accept data.
 * <pre>{@code
 * 		@Override
		public void addCorsMappings(CorsRegistry cors) {
			cors.addMapping("../**").allowedOrigins(allowedOrigins);
		}
 * }</pre>
 * 
 * @author Dasia Melvin
 */
@Configuration
public class BlogSiteAppConfig implements WebMvcConfigurer{
	/** 
	 * Obtains the URL from the applications.properties file with a custom property
	 * through the {@code @Value} annotation.
	 */
	// application.properties - front-end URL (localhost for now)
	@Value("${allowed.origins}")
	private String[] allowedOrigins;
	
	/**
	 * Obtains the endpoint of the backend API from the {@code applications.properties} file
	 * through the {@code @Value} annotation.
	 * <p>
	 * Property provided through Spring Data.
	 */
	// end-point of the API
	@Value("${spring.data.rest.base-path}")
	private String basePath;
	
	// exposes the end-point to the front-end URL
	@Override
	public void addCorsMappings(CorsRegistry cors) {
		cors.addMapping((basePath) + "/**").allowedOrigins(allowedOrigins);
	}
}
