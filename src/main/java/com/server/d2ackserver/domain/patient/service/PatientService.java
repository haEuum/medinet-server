package com.server.d2ackserver.domain.patient.service;

import com.server.d2ackserver.domain.patient.dto.request.SendPatientRequest;
import com.server.d2ackserver.domain.patient.dto.response.PatientInfoResponse;

import java.util.List;

public interface PatientService {
    PatientInfoResponse sendPatientInfo(SendPatientRequest request);
    PatientInfoResponse agreePatient(long patientId); //TODO: ㅈ된 듯
    List<PatientInfoResponse> getAllPatientInfo();
}