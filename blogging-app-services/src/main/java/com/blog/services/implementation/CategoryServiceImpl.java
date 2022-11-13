package com.blog.services.implementation;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.repositories.CategoryRepository;
import com.blog.services.CategoryService;
import com.blog.utils.CategoryMapper;
import com.blog.utils.CategoryMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.mapper = new CategoryMapperImpl();
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }

    @Override
    public CategoryDto getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
    }

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        return mapper.entityToDto(categoryRepository.save(mapper.dtoToEntity(dto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto dto, Integer id) {
        return categoryRepository.findById(id)
                .map(entity -> mapper.update(dto, entity))
                .map(categoryRepository::save)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategory(Integer id) {
        var cat = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category not found"));
        categoryRepository.delete(cat);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> dumpAll(List<CategoryDto> cats) {
        return categoryRepository.saveAll(cats.stream().map(mapper::dtoToEntity).toList())
                .stream().map(mapper::entityToDto)
                .toList();
    }
}
