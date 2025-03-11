package com.blog.blog_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_application.model.Comment;
import com.blog.blog_application.payload.ApiResponse;
import com.blog.blog_application.payload.CommentDto;
import com.blog.blog_application.service.CommentService;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{id}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int id) {
        return new ResponseEntity<CommentDto>(this.commentService.addComment(id, commentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{id}")
    public ApiResponse deleteComment(@PathVariable int id) {
        this.commentService.deleteComment(id);
        return new ApiResponse("Comment deleted successfully!!", true);
    }
}
