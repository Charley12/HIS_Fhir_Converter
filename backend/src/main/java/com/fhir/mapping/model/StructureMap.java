package com.fhir.mapping.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "structure_maps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private MappingSession session;

    @Column(name = "source_field_path", nullable = false)
    private String sourceFieldPath;

    @Column(name = "target_fhir_path", nullable = false)
    private String targetFhirPath;

    @Column(name = "transformation_logic", columnDefinition = "TEXT")
    private String transformationLogic; // JSON or script logic for transformation

    @Column(name = "verified_by")
    private String verifiedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
