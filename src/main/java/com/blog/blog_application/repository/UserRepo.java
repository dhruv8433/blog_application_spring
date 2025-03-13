package com.blog.blog_application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_application.model.User;

// we need interface to create repositories and extends it with jparepository and specify want to work with user table and id type is integer
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
