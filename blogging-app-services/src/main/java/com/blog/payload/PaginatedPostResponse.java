package com.blog.payload;

import java.util.List;

public record PaginatedPostResponse(
   List<PostDto> posts,
   Integer pageNumber,
   Integer pageSize
) {}
