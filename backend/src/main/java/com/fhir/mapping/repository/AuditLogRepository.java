package com.fhir.mapping.repository;

import com.fhir.mapping.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserId(String userId);
    List<AuditLog> findByEntityTypeAndEntityId(String entityType, Long entityId);
}
