package com.blog.services.implementation;

import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payload.PaginatedPostResponse;
import com.blog.payload.PostDto;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;
import com.blog.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    UserRepository userRepository;
    CategoryRepository categoryRepository;
    PostRepository postRepository;
    PostMapper postMapper;
    UserMapper userMapper;
    CategoryMapper categoryMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = new PostMapperImpl();
        this.userMapper = new UserMapperImpl();
        this.categoryMapper = new CategoryMapperImpl();
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id"));
        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        var post = postMapper.dtoToPost(postDto);
        post.setCreatedDate(new Date(System.currentTimeMillis()));
        post.setCategory(category);
        post.setUser(user);
        return postMapper.postToDto(postRepository.save(post));
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::postToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedPostResponse getAllPosts(Integer pageNumber, Integer count) {
        Pageable pagination = PageRequest.of(pageNumber, count);
        return new PaginatedPostResponse(
                postRepository.findAll((PageRequest.of(pageNumber, count))).stream()
                        .map(postMapper::postToDto)
                        .toList(),
                pagination.getPageNumber(),
                pagination.getPageSize()

        );
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        return postRepository.findByUser(
                userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " does not exist"))
                )
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedDate))
                .map(postMapper::postToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        return postRepository.findByCategory(
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ResourceNotFoundException("category by id " + categoryId + " does not exist"))
                )
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedDate))
                .map(postMapper::postToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer id) {
        return postRepository.findById(id)
                .map(postMapper::postToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }

    @Override
    public PostDto getPostByTitle(String title) {
        return postRepository.findByTitle(title)
                .map(postMapper::postToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Post by title " + title + " not found"));
    }

    @Override
    public PostDto updatePostById(PostDto dto, Integer id) {
        return postRepository.findById(id)
                .map(p -> postMapper.update(dto, p))
                .map(postRepository::save)
                .map(postMapper::postToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Post by id " + id + " not found"));
    }

    @Override
    public void deletePostById(Integer id) {
        var post =postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No post found with id " + id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> dump(List<PostDto> posts) {
        var dump = postRepository.saveAll(posts.stream().map(postMapper::dtoToPost).toList());
        return dump.stream().map(postMapper::postToDto).toList();
    }
}
