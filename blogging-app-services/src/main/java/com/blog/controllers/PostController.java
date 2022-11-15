package com.blog.controllers;

import com.blog.payload.ApiResponse;
import com.blog.payload.PostDto;
import com.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    @Autowired
    PostService service;

    // ***************************************************************** DUMP
    @PostMapping("/dump")
    public ResponseEntity<List<PostDto>> dump(@RequestBody List<PostDto> posts) {
        var dump = service.dump(posts);
        return ResponseEntity.ok(dump);
    }
    // *****************************************************************

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        var post = service.createPost(dto, categoryId, userId);
        return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        var posts = service.getAllPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        var posts = service.getAllPostsByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getPostById(@RequestParam(name = "id", required = false) Integer postId,
                                               @RequestParam(name = "title", required = false) String title) {
        return ResponseEntity.ok(Optional.ofNullable(postId)
                .map(service::getPostById)
                .map(List::of)
                .orElse(
                        Optional.ofNullable(title)
                                .map(service::getPostByTitle)
                                .map(List::of)
                                .orElse(service.getAllPosts())
                ));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        service.deletePostById(postId);
        return ResponseEntity.ok((new ApiResponse("deleted")));
    }
}
