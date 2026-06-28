package com.fhir.mapping.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "action", nullable = false)
    private String action; // e.g. APPROVE_MAPPING, REJECT_MAPPING, MODIFY_MAPPING

    @Column(name = "entity_type", nullable = false)
    private String entityType; // e.g. ConceptMap, StructureMap

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
}
