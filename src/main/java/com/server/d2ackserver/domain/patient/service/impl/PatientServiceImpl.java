package com.server.d2ackserver.domain.patient.service.impl;

import com.server.d2ackserver.domain.patient.domain.entity.PatientEntity;
import com.server.d2ackserver.domain.patient.dto.request.SendPatientRequest;
import com.server.d2ackserver.domain.patient.dto.response.PatientInfoResponse;
import com.server.d2ackserver.domain.patient.patient.PatientError;
import com.server.d2ackserver.domain.patient.repository.PatientRepository;
import com.server.d2ackserver.domain.patient.service.PatientService;
import com.server.d2ackserver.global.exception.CustomException;
import com.server.d2ackserver.global.security.holder.SecurityHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final SecurityHolder securityHolder;

    @Override
    public PatientInfoResponse sendPatientInfo(SendPatientRequest request) {

        PatientEntity patient = PatientEntity.builder()
                .name(request.name())
                .agree(false)
                .agreedOrganic(null)
                .build();

        return PatientInfoResponse.of(patientRepository.save(patient));
    }

    @Override
    public PatientInfoResponse agreePatient(long patientId) {
        PatientEntity patient = patientRepository.findById(patientId).orElseThrow(() -> new CustomException(PatientError.USER_NOT_FOUND));
        try {
            patient.setAgree(true);
            patient.setAgreedOrganic(securityHolder.getPrincipal());
        } catch (Exception e) {
            throw new CustomException(PatientError.FAILED_TO_AGREE_PATIENT);
        }
        return PatientInfoResponse.of(patientRepository.save(patient));
    }

    @Override
    public List<PatientInfoResponse> getAllPatientInfo() {

        List<PatientEntity> patients = patientRepository.findAll();
        List<PatientInfoResponse> responsePatients = patients.stream().map((PatientEntity patient) -> PatientInfoResponse.of(patient)).toList();
        return responsePatients;
    }
}
