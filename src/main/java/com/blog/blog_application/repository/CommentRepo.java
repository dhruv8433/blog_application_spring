package com.blog.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_application.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    
}
