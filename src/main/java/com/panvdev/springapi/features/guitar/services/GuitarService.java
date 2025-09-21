package com.panvdev.springapi.features.guitar.services;

import com.panvdev.springapi.core.dtos.PageableAndSortingRequest;
import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.features.guitar.dtos.FilterByBrandAndModelGuitarDto;
import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import com.panvdev.springapi.features.guitar.dtos.GuitarFiltersDto;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface GuitarService {
    Result<List<GuitarDto>> findAll();
    Result<GuitarDto> save(GuitarDto guitarDto);
    Result<GuitarDto> findById(UUID id);
    Result<GuitarDto> delete(UUID id);
    Result<Page<GuitarDto>> findAllByPageAndSorting(PageableAndSortingRequest request);
    Result<List<GuitarDto>> findByBrand(String brand);
    Result<List<GuitarDto>> findByBrandAndModel(FilterByBrandAndModelGuitarDto filters);
    Result<List<GuitarDto>> findByFilters(GuitarFiltersDto filters);
    Result<List<GuitarDto>> findByCategoryId(UUID categoryId);
    Result<List<GuitarDto>> findByCategoryName(String categoryName);
    Result<GuitarDto> updateImage(UUID id, MultipartFile file);
    Resource downloadImage(UUID id);
}
