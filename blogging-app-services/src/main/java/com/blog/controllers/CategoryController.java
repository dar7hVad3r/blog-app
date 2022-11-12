package com.blog.controllers;

import com.blog.payload.ApiResponse;
import com.blog.payload.CategoryDto;
import com.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        var categories = service.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        var category = service.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<CategoryDto> getCategoryByTitle(@PathVariable String title) {
        var category = service.getCategoryByTitle(title);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto dto) {
        var category = service.addCategory(dto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto dto, @PathVariable Integer id) {
        var category = service.updateCategory(dto, id);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        service.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("deleted"),HttpStatus.OK);
    }
}
