package com.acorn_mentor.acorn_mentor.repository;

import com.acorn_mentor.acorn_mentor.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long> {
}