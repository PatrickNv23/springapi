package com.panvdev.springapi.features.category;

import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.features.category.dtos.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Result<CategoryDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }
}
