package com.blog.blog_application.service;

import com.blog.blog_application.payload.CommentDto;

public interface CommentService {
    public CommentDto addComment(int postId, CommentDto commentDto);
    public void deleteComment(int commentId);
    // public List<CommentDto> getComments(int postId);
}
