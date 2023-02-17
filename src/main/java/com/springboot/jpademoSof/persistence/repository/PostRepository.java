package com.springboot.jpademoSof.persistence.repository;

import com.springboot.jpademoSof.persistence.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}