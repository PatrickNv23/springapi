package com.panvdev.springapi.features.category;

import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.features.category.dtos.CategoryDto;

import java.util.UUID;

public interface CategoryService {
    Result<CategoryDto> findById(UUID id);
}
