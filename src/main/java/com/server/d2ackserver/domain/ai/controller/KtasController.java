package com.server.d2ackserver.domain.ai.controller;

import com.server.d2ackserver.domain.ai.dto.request.KtasQuestionnaireDto;
import com.server.d2ackserver.domain.ai.dto.response.KtasResultDto;
import com.server.d2ackserver.domain.ai.service.KtasAnalysisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ktas")
@Tag(name = "KTAS 환자 분류")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class KtasController {

    @Autowired
    private KtasAnalysisService ktasAnalysisService;

    @PostMapping("/analyze")
    public ResponseEntity<KtasResultDto> analyzeKtas(@RequestBody KtasQuestionnaireDto questionnaire) {
        try {
            // 입력값 검증
            if (questionnaire.getPatientAge() == null || questionnaire.getPatientAge() < 0) {
                return ResponseEntity.badRequest().build();
            }

            KtasResultDto result = ktasAnalysisService.analyzeKtasLevel(questionnaire);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OpenAI KTAS Analysis Service is running");
    }
}
