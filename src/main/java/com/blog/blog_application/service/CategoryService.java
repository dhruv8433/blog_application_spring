package com.blog.blog_application.service;

import java.util.List;

import com.blog.blog_application.payload.CategoryDto;

public interface CategoryService {
    // by default all methods declared in an interface are public and abstract

    // create
    CategoryDto createCategory(CategoryDto categoryDto);

    // update
    CategoryDto updateCategory(CategoryDto categoryDto, int id);

    // delete
    void deleteCategory(int id);

    // get
    CategoryDto getCategory(int id);

    // get all
    List<CategoryDto> getAllCategories();
}
