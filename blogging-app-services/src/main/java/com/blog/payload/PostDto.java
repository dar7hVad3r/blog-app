package com.blog.payload;

import com.blog.entities.Category;
import com.blog.entities.User;

public record PostDto(
   String title,
   String content,
   UserDto user,
   CategoryDto category
) {}
