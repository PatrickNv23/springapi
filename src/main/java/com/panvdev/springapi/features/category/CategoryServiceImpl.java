package com.panvdev.springapi.features.category;

import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.core.exceptions.NotFoundException;
import com.panvdev.springapi.features.category.dtos.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result<CategoryDto> findById(UUID id) {
        return Result.success(
                categoryRepository.findById(id)
                        .map(categoryMapper::toDetailDto)
                        .orElseThrow(() -> new NotFoundException("Category not found"))
        );
    }
}
