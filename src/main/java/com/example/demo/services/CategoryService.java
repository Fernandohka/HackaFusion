package com.example.demo.services;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ListPageDto;
import com.example.demo.dto.ResponseDto;

public interface CategoryService {
    public CategoryDto create(String name);
    public ListPageDto<CategoryDto> getAll(String query);
    public ResponseDto delete(Long id);
}
