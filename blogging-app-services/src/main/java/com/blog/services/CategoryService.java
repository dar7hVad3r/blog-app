package com.blog.services;

import com.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto getCategoryById(Integer id);
    public CategoryDto getCategoryByTitle(String title);
    public CategoryDto addCategory(CategoryDto dto);
    public CategoryDto updateCategory(CategoryDto dto, Integer id);
    public void deleteCategory(Integer id);
    public List<CategoryDto> getAllCategories();
}
