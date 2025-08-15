package com.panvdev.springapi.features.guitar.services;

import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import java.util.List;
import java.util.UUID;

public interface GuitarService {
    List<GuitarDto> findAll();
    GuitarDto save(GuitarDto guitarDto);
    GuitarDto findById(UUID id);
    void delete(UUID id);
}
