package com.panvdev.springapi.features.guitar.services;

import com.panvdev.springapi.core.dtos.PageableAndSortingRequest;
import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.core.exceptions.NotFoundException;
import com.panvdev.springapi.features.guitar.domains.Guitar;
import com.panvdev.springapi.features.guitar.dtos.FilterByBrandAndModelGuitarDto;
import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import com.panvdev.springapi.features.guitar.dtos.GuitarFiltersDto;
import com.panvdev.springapi.features.guitar.mappers.GuitarMapper;
import com.panvdev.springapi.features.guitar.repositories.GuitarRepository;
import com.panvdev.springapi.features.guitar.specifications.GuitarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GuitarServiceImpl implements GuitarService {

    @Autowired
    private GuitarRepository guitarRepository;
    @Autowired
    private GuitarMapper guitarMapper;


    @Override
    public Result<List<GuitarDto>> findAll() {
        return Result.success(
                guitarRepository.findAll()
                    .stream()
                    .map(guitarMapper::toDto)
                    .collect(Collectors.toList())
        );
    }

    @Override
    public Result<GuitarDto> save(GuitarDto guitarDto) {
        Guitar savedGuitar = guitarRepository.save(guitarMapper.toEntity(guitarDto));
        return Result.success(guitarMapper.toDto(savedGuitar));
    }

    @Override
    public Result<GuitarDto> findById(UUID id) {
        return Result.success(
                guitarRepository.findById(id)
                    .map(guitarMapper::toDto)
                    .orElseThrow(() -> new NotFoundException("Guitar not found with id: " + id))
        );
    }

    @Override
    public Result<GuitarDto> delete(UUID id) {
        Guitar guitar = guitarRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Guitar not found with id: " + id));
        GuitarDto guitarDto = guitarMapper.toDto(guitar);
        guitarRepository.delete(guitar);
        return Result.success(guitarDto);
    }

    @Override
    public Result<Page<GuitarDto>> findAllByPageAndSorting(PageableAndSortingRequest request) {
        Sort sort = request.isAscending()
                ? Sort.by(request.getSortBy()).ascending()
                : Sort.by(request.getSortBy()).descending();
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<GuitarDto> resultPage = guitarRepository.findAll(pageable)
                .map(guitarMapper::toDto);
        return Result.success(resultPage);
    }

    @Override
    public Result<List<GuitarDto>> findByBrand(String brand) {
        return Result.success(
                guitarRepository.findByBrand(brand)
                        .stream()
                        .map(guitarMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Result<List<GuitarDto>> findByBrandAndModel(FilterByBrandAndModelGuitarDto filters) {
        return Result.success(
                guitarRepository.findByBrandAndModel(filters.brand(), filters.model())
                        .stream()
                        .map(guitarMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Result<List<GuitarDto>> findByFilters(GuitarFiltersDto filters) {
        return Result.success(
                guitarRepository.findAll(GuitarSpecification.withFilters(filters))
                        .stream()
                        .map(guitarMapper::toDto)
                        .toList()
        );
    }

    @Override
    public Result<List<GuitarDto>> findByCategoryId(UUID categoryId) {
        return Result.success(
                guitarRepository.findByCategoryId(categoryId).stream()
                        .map(guitarMapper::toDto)
                        .toList()
        );
    }

    @Override
    public Result<List<GuitarDto>> findByCategoryName(String categoryName) {
        return Result.success(
                guitarRepository.findByCategoryName(categoryName).stream()
                        .map(guitarMapper::toDto)
                        .toList()
        );
    }
}
