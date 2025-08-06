package com.server.d2ackserver.domain.hospital.service.impl;

import com.server.d2ackserver.domain.hospital.domain.repository.HospitalRepository;
import com.server.d2ackserver.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
}
