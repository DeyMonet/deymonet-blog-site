package com.deymonet.blogsite.config;

import com.okta.spring.boot.oauth.Okta;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Enables security for the backend API with {@link WebSecurityConfigurerAdapter}.
 * <p>
 * 
 * This class uses Spring Security and Okta to allow authenticated users to use
 * the CRUD endpoints of the {@code BlogController}.
 * 
 * The frontend also needs to communicate and pass in a token for authentication to use
 * the endpoints.
 * <pre>{@code
*       @Override
        protected void configure(HttpSecurity http) throws Exception {
            // ...
            .and()
            .oauth2Login()
            .and()
            .oauth2ResourceServer().jwt();

            http.cors(); // enables cross origin for the backend
        }
 * </pre>
 * <p>
 * 
 * @author Dasia Melvin
 * @see com.deymonet.blogsite.controller.BlogController
 * 
 */
@Configuration
@EnableWebSecurity
public class BlogSiteAppSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // Restricted access to the BlogController endpoints unless authenticated
            .antMatchers("/api/blog_v1/blog/**")
            .authenticated()
            // Authenticated users through Okta sign-in with OAuth 2.0.
            .and()
            .oauth2Login()
            .and()
            .oauth2ResourceServer().jwt();
        
        Okta.configureResourceServer401ResponseBody(http);

        // Enabling Cross Origins, disabling CSRF
        http.cors();
        http.csrf().disable();
    }
}
