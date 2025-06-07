package com.oauth2.jwt.post.repository;

import com.oauth2.jwt.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
