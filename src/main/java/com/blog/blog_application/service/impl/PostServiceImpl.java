package com.blog.blog_application.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_application.errors.ResourceNotFoundException;
import com.blog.blog_application.model.Category;
import com.blog.blog_application.model.Post;
import com.blog.blog_application.model.User;
import com.blog.blog_application.payload.PostDto;
import com.blog.blog_application.repository.CategoryRepo;
import com.blog.blog_application.repository.PostRepo;
import com.blog.blog_application.repository.UserRepo;
import com.blog.blog_application.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private org.modelmapper.ModelMapper ModelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        // fetch user first
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        // fetch category
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        Post post = this.ModelMapper.map(postDto, Post.class); // set title, content
        post.setPostImage("default.png"); // set image
        post.setAddedDate(new Date()); // set date
        post.setUser(user); // set user
        post.setCategory(category); // set category

        Post newPost = this.postRepo.save(post);
        PostDto savedPost = this.ModelMapper.map(newPost, PostDto.class);
        return savedPost;
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

    @Override
    public void deletePost(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public PostDto getPostById(int id) {
        Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));

        PostDto postDtos = this.ModelMapper.map(post, PostDto.class);
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();

        List<PostDto> postDtos = posts.stream().map(post -> {
            return this.ModelMapper.map(post, PostDto.class);
        }).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(int userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "Id", userId));
        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map(post -> {
            return this.ModelMapper.map(post, PostDto.class);
        }).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByCategory(int categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = posts.stream().map(post -> {
            return this.ModelMapper.map(post, PostDto.class);
        }).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}