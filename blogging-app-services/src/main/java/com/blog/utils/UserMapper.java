package com.blog.utils;

import org.mapstruct.Mapper;

import com.blog.entities.User;
import com.blog.payload.UserDto;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "Spring")
public interface UserMapper {
	public User toEntity(UserDto dto);
	public UserDto toDto(User user);
	@Mapping(target = "id", ignore = true)
	public User updateUser(UserDto dto, @MappingTarget User user);
}
