package com.server.d2ackserver.domain.patient.repository;

import com.server.d2ackserver.domain.patient.domain.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
