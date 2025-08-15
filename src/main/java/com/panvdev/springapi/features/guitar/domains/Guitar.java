package com.panvdev.springapi.features.guitar.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "guitar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guitar {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String model;
    private String brand;
    private Double price;
}
