package com.panvdev.springapi.features.guitar;

import com.panvdev.springapi.features.guitar.dtos.GuitarFiltersRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GuitarSpecification {
    public static Specification<Guitar> withFilters(GuitarFiltersRequest filters){
        return (root, _, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // filter by Brand
            if(filters.brand() != null){
                predicates.add(
                        root.get("brand").in(filters.brand())
                );
            }

            // filter by model (like)
            if(filters.model() != null){
                predicates.add(
                  cb.like(cb.lower(root.get("model")), "%" + filters.model().toLowerCase() + "%")
                );
            }

            // filter greater than price
            if(filters.price() != null){
                predicates.add(
                        cb.greaterThan(root.get("price"), filters.price())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
