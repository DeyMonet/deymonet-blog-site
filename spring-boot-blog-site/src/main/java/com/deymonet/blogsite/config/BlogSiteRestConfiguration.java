package com.deymonet.blogsite.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.deymonet.blogsite.entity.Topic;
import com.deymonet.blogsite.entity.User;

/**
 * Allows the backend API data to be displayed to the frontend application.
 * <p>
 * This class implements the {@link RepositoryRestConfigurer} interface in order to configure entity functionality.
 * Using the method provided by the implemented class will help allow and disable certain http methods for different entity classes.
 * This also allows the backend API to communicate to the frontend through CORS for the same configuration.
 * 
 * <pre>{@code
 * @Override
 * public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
 * 	// cofiguration code
 * }
 * }</pre>
 * 
 * @author Dasia Melvin
 */
@Configuration
public class BlogSiteRestConfiguration implements RepositoryRestConfigurer{
	/**See {@link com.deymonet.blogsite.config.BlogSiteAppConfig} */
	@Value("${allowed.origins}")
	private String[] allowedOrigins;
	
	/**See {@link EntityManager} */
	private EntityManager entityManager;
	
	@Autowired
	public BlogSiteRestConfiguration(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// Visitors are to look through the blog. They cannot create accounts.
		// The same with the entire site - It's there to be viewed. Nothing else.
		HttpMethod[] disabledMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
		
		disableHttpMethod(Topic.class, config, disabledMethods);
		disableHttpMethod(User.class, config, disabledMethods);
		
		showEntityIds(config);
		
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
	}
	
	/**
	 * Configures classes that are not allowed to use certain http methods
	 * 
	 * @param theClass - Classes existing in the application
	 * @param config - See {@link RepositoryRestConfiguration}
	 * @param methods - The HTTP Requests (i.e.: GET, POST, PUT, DELETE, ...)
	 */
	private void disableHttpMethod(Class<?> theClass, RepositoryRestConfiguration config, HttpMethod[] methods) {
		config.getExposureConfiguration()
			.forDomainType(theClass)
			.withItemExposure( (metadata, httpMethods) -> httpMethods.disable(methods))
			.withCollectionExposure( (metadata, httpMethods) -> httpMethods.disable(methods));
	}
	
	/**
	 * Obtains the information from the database and displays them to the frontend.
	 * 
	 * @param config - See {@link RepositoryRestConfiguration}
	 */
	@SuppressWarnings("rawtypes")
	private void showEntityIds(RepositoryRestConfiguration config) {
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType entityType : entities) {
			entityClasses.add(entityType.getJavaType());
		}
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
}
