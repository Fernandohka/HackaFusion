package com.example.demo.dto.Web;

public record PageableQueryDto(
    Integer page,
    Integer size,
    String query
) {
    
}
