package com.blog.blog_application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_application.payload.PostDto;
import com.blog.blog_application.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    // GET by User
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId) {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // GET by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId) {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // GET all posts
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = this.postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getMethodName(@PathVariable int id) {
        return new ResponseEntity<PostDto>(this.postService.getPostById(id), HttpStatus.OK); 
    }
    
}
