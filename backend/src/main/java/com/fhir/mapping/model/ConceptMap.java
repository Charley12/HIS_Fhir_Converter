package com.fhir.mapping.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "concept_maps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConceptMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private MappingSession session;

    @Column(name = "source_code_system")
    private String sourceCodeSystem;

    @Column(name = "source_code", nullable = false)
    private String sourceCode;

    @Column(name = "target_code_system")
    private String targetCodeSystem;

    @Column(name = "target_code", nullable = false)
    private String targetCode;

    @Column(name = "equivalence_status")
    private String equivalenceStatus; // e.g. equivalent, broader, narrower, unmapped

    @Column(name = "verified_by")
    private String verifiedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
