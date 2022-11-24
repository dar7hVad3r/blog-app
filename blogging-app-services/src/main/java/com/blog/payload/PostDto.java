package com.blog.payload;

import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.User;

import java.util.Set;

public record PostDto(
   String title,
   String content,
   UserDto user,
   CategoryDto category,
   Set<Comment> comments
) {}
