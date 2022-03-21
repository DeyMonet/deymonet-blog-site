package com.deymonet.blogsite.dao;

import java.util.List;

// import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.deymonet.blogsite.entity.Post;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long>{
	List<Post> findByTopicTagOrderByPublishedAsc(@RequestParam("topicName") String topicName);
	List<Post> findFirst5ByOrderByPublishedDesc();
	Post findByTitle(@RequestParam("title") String title);
}
