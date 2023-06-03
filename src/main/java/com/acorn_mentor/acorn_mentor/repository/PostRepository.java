package com.acorn_mentor.acorn_mentor.repository;

import com.acorn_mentor.acorn_mentor.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}