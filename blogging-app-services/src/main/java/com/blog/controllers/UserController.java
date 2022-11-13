package com.blog.controllers;

import com.blog.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.payload.UserDto;
import com.blog.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/v1/user")
@RestController
public class UserController {

	@Autowired
	private UserService service;

	// ***************************************************************** DUMP
	@PostMapping("/dump")
	public ResponseEntity<List<UserDto>> userDump(@Valid @RequestBody List<UserDto> dtos) {
		var dump = service.dump(dtos);
		return ResponseEntity.ok(dump);
	}
	// *****************************************************************

	//POST create user
	@PostMapping
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto dto) {
		return new ResponseEntity<UserDto>(service.createUser(dto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto, @PathVariable Integer id) {
		var user = service.updateUser(dto, id);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
		service.deleteUser(id);
		return ResponseEntity.ok(new ApiResponse("User Not Found"));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
		var user = service.getUser(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		var users = service.getAllUsers();
		return ResponseEntity.ok(users);
	}

}
