package com.blog.blog_application.service;

import java.util.List;

import com.blog.blog_application.payload.PostDto;
import com.blog.blog_application.payload.PostResponse;

public interface PostService {

    // create
    PostDto createPost(PostDto postDto, int userId, int categoryId);

    // upadte
    PostDto updatePost(PostDto postDto, int id);

    //  delete
    void deletePost(int id);

    // read
    PostDto getPostById(int id);

    // read all
    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    // read by user
    List<PostDto> getPostsByUser(int userId);

    // read by category
    List<PostDto> getPostsByCategory(int categoryId);

    //search 
    List<PostDto> searchPosts(String keyword);
    
}
