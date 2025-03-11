package com.blog.blog_application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blog_application.config.AppConstances;
import com.blog.blog_application.payload.ApiResponse;
import com.blog.blog_application.payload.PostDto;
import com.blog.blog_application.payload.PostResponse;
import com.blog.blog_application.service.FileService;
import com.blog.blog_application.service.PostService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;


    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @PathVariable int userId,
            @PathVariable int categoryId) throws IOException {

        PostDto postDto = new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);

        // Upload image if present
        if (image != null && !image.isEmpty()) {
            System.out.println("Uploading image" + image.getName());
            String uploadedFileName = fileService.uploadImage(AppConstances.uploadDir, image);
            postDto.setPostImage(uploadedFileName); // Set the uploaded image name
        } else {
            postDto.setPostImage("default.png"); // Default image if none uploaded
        }

        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
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
    @GetMapping // apply pagination using request params
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "page", defaultValue = AppConstances.PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstances.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstances.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstances.SORT_DIR, required = false) String sortDir
            ) {
        PostResponse postResponse = this.postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getMethodName(@PathVariable int id) {
        return new ResponseEntity<PostDto>(this.postService.getPostById(id), HttpStatus.OK);
    }

    // DELETE by ID
    @DeleteMapping("/{id}")
    public ApiResponse deletePost(@PathVariable int id) {
        this.postService.deletePost(id);
        return new ApiResponse("Post Deleted Successfully!", true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatedPost(@PathVariable int id, @RequestBody PostDto postDto) {
        return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, id), HttpStatus.OK);
    }

    // search
    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam(value = "keyword") String keyword) {
        List<PostDto> posts = this.postService.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // POST Image upload

    // public ResponseEntity<ImageResponse> uploadToImage()
}
