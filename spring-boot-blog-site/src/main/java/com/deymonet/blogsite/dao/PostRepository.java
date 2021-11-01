package com.deymonet.blogsite.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.deymonet.blogsite.entity.Post;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long>{
	List<Post> findByTopicTag(@RequestParam("topicName") String topicName, Sort sort);
	Post findByTitle(@RequestParam("title") String title);
}
