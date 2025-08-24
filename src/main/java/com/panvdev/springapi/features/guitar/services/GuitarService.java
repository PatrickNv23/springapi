package com.panvdev.springapi.features.guitar.services;

import com.panvdev.springapi.core.dtos.PageableAndSortingRequest;
import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface GuitarService {
    Result<List<GuitarDto>> findAll();
    Result<GuitarDto> save(GuitarDto guitarDto);
    Result<GuitarDto> findById(UUID id);
    Result<GuitarDto> delete(UUID id);
    Result<Page<GuitarDto>> findAllByPageAndSorting(PageableAndSortingRequest request);
}
