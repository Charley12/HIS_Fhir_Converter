package com.fhir.mapping.repository;

import com.fhir.mapping.model.ConceptMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConceptMapRepository extends JpaRepository<ConceptMap, Long> {
    List<ConceptMap> findBySessionId(Long sessionId);
}
