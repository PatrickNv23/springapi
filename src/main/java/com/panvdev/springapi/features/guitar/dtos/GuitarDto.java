package com.panvdev.springapi.features.guitar.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuitarDto {
    private UUID id;

    @NotBlank(message = "The model field is mandatory")
    private String model;

    @Size(min = 3, max = 40, message = "The brand field must be between 3 and 40 characters")
    private String brand;

    @NotNull(message = "The price field is mandatory")
    @Positive(message = "The price field must be positive")
    @Min(value = 500, message = "The price field should not be less than 500")
    private Double price;
}
