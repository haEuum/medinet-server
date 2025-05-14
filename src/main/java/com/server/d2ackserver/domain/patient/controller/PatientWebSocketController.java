package com.server.d2ackserver.domain.patient.controller;


import com.server.d2ackserver.domain.patient.dto.request.SendPatientRequest;
import com.server.d2ackserver.domain.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PatientWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final PatientService patientService;

    @MessageMapping("/sendPatient")
    public void handlePostMessage(SendPatientRequest request) {
        messagingTemplate.convertAndSend("/topic/posts", patientService.sendPatientInfo(request));
    }
}
