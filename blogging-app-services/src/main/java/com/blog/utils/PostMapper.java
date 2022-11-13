package com.blog.utils;

import com.blog.entities.Post;
import com.blog.payload.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "string")
public interface PostMapper {
    Post dtoToPost(PostDto dto);
    PostDto postToDto(Post post);
    Post update(PostDto dto, @MappingTarget Post post);
}
