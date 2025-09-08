package com.panvdev.springapi.features.category.services;

import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.features.category.dtos.CategoryDto;

import java.util.UUID;

public interface CategoryService {
    Result<CategoryDto> findById(UUID id);
}
