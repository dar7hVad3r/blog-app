package com.blog.utils;

import com.blog.entities.Category;
import com.blog.payload.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryDto entityToDto(Category category);
    public Category dtoToEntity(CategoryDto dto);
    @Mapping(target = "id", ignore = true)
    public Category update(CategoryDto dto, @MappingTarget Category category);
}
