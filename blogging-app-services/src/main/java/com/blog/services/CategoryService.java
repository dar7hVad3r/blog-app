package com.blog.services;

import com.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(Integer id);
    CategoryDto getCategoryByTitle(String title);
    CategoryDto addCategory(CategoryDto dto);
    CategoryDto updateCategory(CategoryDto dto, Integer id);
    void deleteCategory(Integer id);
    List<CategoryDto> getAllCategories();

    List<CategoryDto> dumpAll(List<CategoryDto> cats);
}
