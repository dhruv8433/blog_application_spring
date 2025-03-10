package com.blog.blog_application.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty(message = "Category title cannot be empty")
    @Size(min = 3, max = 50, message = "Category title must be between 3 and 50 characters")
    private String categoryTitle;
    @NotEmpty(message = "Category description cannot be empty")
    private String categoryDescription;
}
