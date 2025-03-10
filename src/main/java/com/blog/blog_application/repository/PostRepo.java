package com.blog.blog_application.repository;

import java.util.List;
import com.blog.blog_application.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_application.model.Post;
import com.blog.blog_application.model.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category cat);

}
