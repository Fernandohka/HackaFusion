package com.example.demo.dto;

public record DoubleListPageDto<T>(
    ListPageDto<T> yours,
    ListPageDto<T> others
) {
    
}
