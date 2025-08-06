package com.server.d2ackserver.domain.hospital.domain.repository;

import com.server.d2ackserver.domain.hospital.domain.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
    HospitalEntity findByPhoneNumber(String phoneNumber);
}
