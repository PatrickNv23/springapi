package com.panvdev.springapi.features.category.repositories;

import com.panvdev.springapi.features.category.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
