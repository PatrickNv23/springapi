package com.panvdev.springapi.features.guitar.controllers;

import com.panvdev.springapi.core.dtos.PageableAndSortingRequest;
import com.panvdev.springapi.core.error_handling.Result;
import com.panvdev.springapi.features.guitar.dtos.FilterByBrandAndModelGuitarDto;
import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import com.panvdev.springapi.features.guitar.dtos.GuitarFiltersDto;
import com.panvdev.springapi.features.guitar.services.GuitarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guitar")
public class GuitarController {
    @Autowired
    private GuitarService guitarService;

    @GetMapping
    public ResponseEntity<Result<List<GuitarDto>>> findAll() {
        return ResponseEntity.ok(guitarService.findAll());
    }

    @PostMapping
    public ResponseEntity<Result<GuitarDto>> save(@Valid @RequestBody GuitarDto guitarDto) {
        return ResponseEntity.ok(guitarService.save(guitarDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<GuitarDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(guitarService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<GuitarDto>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(guitarService.delete(id));
    }

    @GetMapping("byPageAndSorting")
    public ResponseEntity<Result<Page<GuitarDto>>> findAllByPageAndSorting(@Valid @ModelAttribute PageableAndSortingRequest request){
        return ResponseEntity.ok(guitarService.findAllByPageAndSorting(request));
    }

    @GetMapping("byBrand")
    public ResponseEntity<Result<List<GuitarDto>>> findByBrand(@RequestParam String brand){
        return ResponseEntity.ok(guitarService.findByBrand(brand));
    }

    @GetMapping("byBrandAndModel")
    public ResponseEntity<Result<List<GuitarDto>>> findByBrandAndModel(FilterByBrandAndModelGuitarDto filters){
        return ResponseEntity.ok(guitarService.findByBrandAndModel(filters));
    }

    @GetMapping("byFilters")
    public ResponseEntity<Result<List<GuitarDto>>> findByFilters(@Valid GuitarFiltersDto filters){
        return ResponseEntity.ok(guitarService.findByFilters(filters));
    }

    @GetMapping("byCategory/{categoryId}")
    public ResponseEntity<Result<List<GuitarDto>>> findByCategoryId(@PathVariable UUID categoryId){
        return ResponseEntity.ok(guitarService.findByCategoryId(categoryId));
    }

    @GetMapping("byCategoryName")
    public ResponseEntity<Result<List<GuitarDto>>> findByCategoryId(@RequestParam String categoryName){
        return ResponseEntity.ok(guitarService.findByCategoryName(categoryName));
    }

    @PostMapping(value = "{id}/image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Result<GuitarDto>> updateImage(@PathVariable UUID id, @RequestParam MultipartFile file){
        return ResponseEntity.ok(guitarService.updateImage(id, file));
    }

    @GetMapping("{id}/image/download")
    public ResponseEntity<Resource> downloadImage(@PathVariable UUID id){
        Resource file = guitarService.downloadImage(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
