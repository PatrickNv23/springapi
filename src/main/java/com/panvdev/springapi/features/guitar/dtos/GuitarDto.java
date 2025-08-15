package com.panvdev.springapi.features.guitar.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuitarDto {
    private UUID id;
    private String model;
    private String brand;
    private Double price;
}
