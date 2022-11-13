package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payload.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer id);
	UserDto getUser(Integer id);
	List<UserDto> getAllUsers();
	void deleteUser(Integer id);
	List<UserDto> dump(List<UserDto> dtos);
}
