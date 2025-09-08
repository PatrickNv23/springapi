package com.panvdev.springapi.features.guitar.repositories;

import com.panvdev.springapi.features.guitar.domains.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GuitarRepository extends JpaRepository<Guitar, UUID>, JpaSpecificationExecutor<Guitar> {
    List<Guitar> findByBrand(String brand);
    List<Guitar> findByBrandAndModel(String brand, String model);
    List<Guitar> findByCategoryId(UUID categoryId);
    @Query("SELECT g FROM Guitar g JOIN g.category c WHERE c.name = :categoryName")
    List<Guitar> findByCategoryName(@Param("categoryName") String categoryName);
}
