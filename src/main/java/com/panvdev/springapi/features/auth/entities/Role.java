package com.panvdev.springapi.features.auth.entities;

import com.panvdev.springapi.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"customers"}) // evita recursión
@EqualsAndHashCode(exclude = {"customers"}, callSuper = false)
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true, length = 120)
    private String name;
    @Column(unique = true, length = 120)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers = new HashSet<>();
}
