package com.blog.services;

import com.blog.payload.CategoryDto;
import com.blog.payload.PostDto;
import com.blog.payload.UserDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
    List<PostDto> getAllPosts();
    List<PostDto> getAllPostsByUser(Integer userId);
    List<PostDto> getAllPostsByCategory(Integer categoryId);
    PostDto getPostById(Integer id);
    PostDto updatePostById(PostDto dto, Integer id);
    void deletePostById(Integer id);

    List<PostDto> dump(List<PostDto> posts);

    PostDto getPostByTitle(String title);
}
