package com.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record CategoryDto(
    Integer id,

    @NotEmpty(message = "title should not be empty")
    @Size(min = 3, max = 30, message = "title should be between length 3 -> 30")
    String title,

    @NotEmpty(message = "description should not be empty")
    @Size(min = 3, max = 100, message = "description should be between length 3 -> 100")
    String description
) { }
