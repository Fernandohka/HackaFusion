package com.example.demo.dto;

public record ArchivesProjectDto(
    Long id,
    String name, 
    String archive, 
    ProjectDto project
) {
    
}
