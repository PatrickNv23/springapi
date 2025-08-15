package com.panvdev.springapi.features.guitar.repositories;

import com.panvdev.springapi.features.guitar.domains.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GuitarRepository extends JpaRepository<Guitar, UUID> { }
