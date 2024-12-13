package com.example.demo.dto.Web;


public record CreateProjectDto(
    String name, 
    String description,
    Long idCategory
) {
    
}
