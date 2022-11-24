package com.blog.utils;

import com.blog.entities.Comment;
import com.blog.payload.CommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentDto dto);
    CommentDto toDto(Comment comment);
}
