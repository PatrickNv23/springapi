package com.panvdev.springapi.features.category;

import com.panvdev.springapi.features.category.dtos.CategoryDto;
import com.panvdev.springapi.features.guitar.GuitarMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {GuitarMapper.class})
public interface CategoryMapper {
    CategoryDto toDetailDto(Category category);
}
