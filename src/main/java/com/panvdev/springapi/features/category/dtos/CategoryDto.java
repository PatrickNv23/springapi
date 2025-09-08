package com.panvdev.springapi.features.category.dtos;

import com.panvdev.springapi.features.guitar.dtos.GuitarDto;

import java.util.List;
import java.util.UUID;

public record CategoryDto(UUID id, String name, List<GuitarDto> guitars) { }
