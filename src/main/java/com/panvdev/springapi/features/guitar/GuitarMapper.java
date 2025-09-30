package com.panvdev.springapi.features.guitar;

import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // dependency injection
public interface GuitarMapper {
    GuitarDto toDto(Guitar guitar);
    Guitar toEntity(GuitarDto guitarDto);
}
