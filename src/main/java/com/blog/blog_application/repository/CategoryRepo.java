package com.blog.blog_application.repository;

import com.blog.blog_application.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
}
