package com.blog.blog_application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_application.payload.CategoryDto;
import com.blog.blog_application.repository.CategoryRepo;
import com.blog.blog_application.service.CategoryService;
import com.blog.blog_application.errors.ResourceNotFoundException;
import com.blog.blog_application.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    // create categories
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category savedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCat, CategoryDto.class);
    }

    // update categories
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
       Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));

       cat.setCategoryTitle(categoryDto.getCategoryTitle());
       cat.setCategoryDescription(categoryDto.getCategoryDescription());

       this.categoryRepo.save(cat);

       return this.modelMapper.map(cat, CategoryDto.class);
    }

    // delete categories
    @Override
    public void deleteCategory(int id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));
        this.categoryRepo.delete(cat);
    }

    // get categories by id
    @Override
    public CategoryDto getCategory(int id) {
        Category cat = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    // get all categories
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        return categories.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
    }
    
}
