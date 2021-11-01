package com.deymonet.blogsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deymonet.blogsite.entity.Topic;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "topic", path="topic")
public interface TopicRepository extends JpaRepository<Topic, Long>{

}
