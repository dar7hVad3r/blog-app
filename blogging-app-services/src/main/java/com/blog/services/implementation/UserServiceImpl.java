package com.blog.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import com.blog.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.payload.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import com.blog.utils.UserMapper;
import com.blog.utils.UserMapperImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	private UserMapper mapper = new UserMapperImpl();
	
	@Override
	public UserDto createUser(UserDto user) {
		return mapper.toDto(userRepository.save(mapper.toEntity(user)));
	}

	@Override
	public UserDto updateUser(UserDto user, Integer id) {
		return userRepository.findById(id)
				.map(u -> mapper.updateUser(user, u))
				.map(userRepository::save)
				.map(mapper::toDto)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public UserDto getUser(Integer id) {
		return userRepository.findById(id)
				.map(mapper::toDto)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer id) {
		var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> dump(List<UserDto> dtos) {
		var dump = userRepository.saveAll(dtos.stream().map(mapper::toEntity).toList());
		return dump.stream().map(mapper::toDto).toList();
	}

}
