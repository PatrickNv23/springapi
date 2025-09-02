package com.panvdev.springapi.features.guitar.specifications;

import com.panvdev.springapi.features.guitar.domains.Guitar;
import com.panvdev.springapi.features.guitar.dtos.GuitarFiltersDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GuitarSpecification {
    public static Specification<Guitar> withFilters(GuitarFiltersDto filters){
        return (root, query, cb) -> {
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
