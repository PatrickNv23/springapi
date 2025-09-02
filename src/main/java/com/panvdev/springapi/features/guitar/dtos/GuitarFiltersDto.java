package com.panvdev.springapi.features.guitar.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record GuitarFiltersDto(@NotBlank String brand,@NotBlank String model, @PositiveOrZero Double price) { }
