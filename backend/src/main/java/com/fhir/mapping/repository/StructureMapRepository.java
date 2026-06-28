package com.fhir.mapping.repository;

import com.fhir.mapping.model.StructureMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureMapRepository extends JpaRepository<StructureMap, Long> {
    List<StructureMap> findBySessionId(Long sessionId);
}
