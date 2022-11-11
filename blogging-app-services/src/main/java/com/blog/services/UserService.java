package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payload.UserDto;

public interface UserService {
	public UserDto createUser(UserDto user);
	public UserDto updateUser(UserDto user, Integer id);
	public UserDto getUser(Integer id);
	public List<UserDto> getAllUsers();
	public void deleteUser(Integer id);
}
