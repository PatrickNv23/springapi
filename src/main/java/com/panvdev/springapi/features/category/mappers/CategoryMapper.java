package com.panvdev.springapi.features.category.mappers;

import com.panvdev.springapi.features.category.domains.Category;
import com.panvdev.springapi.features.category.dtos.CategoryDto;
import com.panvdev.springapi.features.guitar.mappers.GuitarMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GuitarMapper.class})
public interface CategoryMapper {
    CategoryDto toDetailDto(Category category);
}
