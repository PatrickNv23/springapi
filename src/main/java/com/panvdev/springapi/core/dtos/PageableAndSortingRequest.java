package com.panvdev.springapi.core.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableAndSortingRequest {
    @PositiveOrZero
    private int page;
    @Positive
    private int size;
    @NotBlank(message = "The sortBy field is mandatory")
    private String sortBy;
    private boolean ascending;
}
