package com.server.d2ackserver.domain.patient.controller;

import com.server.d2ackserver.domain.patient.dto.request.SendPatientRequest;
import com.server.d2ackserver.domain.patient.dto.response.PatientInfoResponse;
import com.server.d2ackserver.domain.patient.service.PatientService;
import com.server.d2ackserver.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@Tag(name = "환자 API")
public class PatientController {
    private final PatientService patientService;
    private final SimpMessagingTemplate messagingTemplate;

    @Operation(summary = "환자 정보 전송", description = "")
    @PostMapping("/send")
    public ResponseEntity<BaseResponse<PatientInfoResponse>> sendPatientInfo(@RequestBody SendPatientRequest request) {
        messagingTemplate.convertAndSend("/medinet/posts", request);
        return BaseResponse.of(patientService.sendPatientInfo(request), 200, "환자 정보 전송 성공");
    }

    @Operation(summary = "환자 승인", description = "사용 금지")
    @PatchMapping("/agree/{patientId}")
    public ResponseEntity<BaseResponse<PatientInfoResponse>> agreePatient(@PathVariable long patientId) {
        return BaseResponse.of(patientService.agreePatient(patientId), 200, "환자 승인 성공");
    }

    @GetMapping
    @Operation(summary = "환자 목록 불러오기")
    public List<PatientInfoResponse> getAllPosts() {
        return patientService.getAllPatientInfo();
    }
}
