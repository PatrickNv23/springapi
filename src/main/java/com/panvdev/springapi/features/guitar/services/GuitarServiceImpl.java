package com.panvdev.springapi.features.guitar.services;

import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.core.exceptions.NotFoundException;
import com.panvdev.springapi.features.guitar.domains.Guitar;
import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import com.panvdev.springapi.features.guitar.repositories.GuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GuitarServiceImpl implements GuitarService {

    @Autowired
    private GuitarRepository guitarRepository;


    @Override
    public Result<List<GuitarDto>> findAll() {
        return Result.success(
                guitarRepository.findAll()
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList())
        );
    }

    @Override
    public Result<GuitarDto> save(GuitarDto guitarDto) {
        Guitar guitar = mapToEntity(guitarDto);
        Guitar savedGuitar = guitarRepository.save(guitar);
        return Result.success(mapToDto(savedGuitar));
    }

    @Override
    public Result<GuitarDto> findById(UUID id) {
        return Result.success(
                guitarRepository.findById(id)
                    .map(this::mapToDto)
                    .orElseThrow(() -> new NotFoundException("Guitar not found with id: " + id))
        );
    }

    @Override
    public Result<GuitarDto> delete(UUID id) {
        Guitar guitar = guitarRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Guitar not found with id: " + id));
        GuitarDto guitarDto = mapToDto(guitar);
        guitarRepository.delete(guitar);
        return Result.success(guitarDto);
    }

    private GuitarDto mapToDto(Guitar guitar){
        return GuitarDto.builder()
                .id(guitar.getId())
                .model(guitar.getModel())
                .brand(guitar.getBrand())
                .price(guitar.getPrice())
                .build();
    }

    private Guitar mapToEntity(GuitarDto guitarDto){
        return Guitar.builder()
                .id(guitarDto.getId())
                .model(guitarDto.getModel())
                .brand(guitarDto.getBrand())
                .price(guitarDto.getPrice())
                .build();
    }
}
