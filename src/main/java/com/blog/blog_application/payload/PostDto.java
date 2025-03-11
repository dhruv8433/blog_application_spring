package com.blog.blog_application.payload;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;

    private String title;
    
    private String content;
    
    private String postImage;
    
    private CategoryDto category;
    
    private UserDto user;
    
    private Date addedDate;
}
