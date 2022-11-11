package com.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserDto(
			Integer id,
			@NotEmpty(message = "Name should not be empty")
			@Size(min = 3, max = 50, message = "name should be between 3 to 50 length")
			String name,
			@Email(message = "email not valid")
			String email,
			@NotEmpty

			String password,
			@NotNull
			String about
		) {}
