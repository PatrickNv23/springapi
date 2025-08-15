package com.panvdev.springapi.features.guitar.controllers;

import com.panvdev.springapi.features.guitar.dtos.GuitarDto;
import com.panvdev.springapi.features.guitar.services.GuitarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guitar")
public class GuitarController {
    @Autowired
    private GuitarService guitarService;

    @GetMapping
    public ResponseEntity<List<GuitarDto>> findAll() {
        return ResponseEntity.ok(guitarService.findAll());
    }

    @PostMapping
    public ResponseEntity<GuitarDto> save(@RequestBody GuitarDto guitarDto) {
        return ResponseEntity.ok(guitarService.save(guitarDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuitarDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(guitarService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        guitarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
