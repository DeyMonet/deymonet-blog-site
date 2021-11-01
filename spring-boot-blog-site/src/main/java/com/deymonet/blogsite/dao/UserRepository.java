package com.deymonet.blogsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.deymonet.blogsite.entity.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
