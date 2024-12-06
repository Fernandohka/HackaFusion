package com.example.demo.dto;

import java.util.List;

public record ListPageDto<T>(
    Integer numPage,
    List<T> listObject
) {
}