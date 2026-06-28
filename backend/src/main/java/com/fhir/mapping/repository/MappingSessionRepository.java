package com.fhir.mapping.repository;

import com.fhir.mapping.model.MappingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MappingSessionRepository extends JpaRepository<MappingSession, Long> {
    List<MappingSession> findByUserId(String userId);
}
