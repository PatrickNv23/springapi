package com.panvdev.springapi.core.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableAndSortingRequest {
    private int page;
    private int size;
    private String sortBy;
    private boolean ascending;
}
