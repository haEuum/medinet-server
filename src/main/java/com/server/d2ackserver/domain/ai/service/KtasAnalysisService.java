package com.server.d2ackserver.domain.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.d2ackserver.domain.ai.dto.request.KtasQuestionnaireDto;
import com.server.d2ackserver.domain.ai.dto.response.KtasResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KtasAnalysisService {

    @Autowired
    private OpenAIService openAIService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public KtasResultDto analyzeKtasLevel(KtasQuestionnaireDto questionnaire) {
        try {
            String prompt = createAnalysisPrompt(questionnaire);
            String aiResponse = openAIService.callOpenAI(prompt);

            // JSON 응답 파싱
            KtasResultDto result = parseAIResponse(aiResponse);

            // 기본 규칙 기반 검증 추가
            result = validateAndAdjustResult(result, questionnaire);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return createFallbackResult(questionnaire);
        }
    }

    private String createAnalysisPrompt(KtasQuestionnaireDto q) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("환자 정보 및 증상 분석을 위한 KTAS 분류를 요청합니다.\n\n");

        prompt.append("환자 정보:\n");
        prompt.append("- 나이: ").append(q.getPatientAge()).append("세\n");
        prompt.append("- 의식 상태: ").append(q.getIsConsciousClear() ? "명료함" : "의식 저하").append("\n");
        prompt.append("- 호흡곤란: ").append(q.getHasBreathingDifficulty() ? "있음" : "없음").append("\n");
        prompt.append("- 출혈: ").append(q.getHasBleeding() ? "있음" : "없음").append("\n");
        prompt.append("- 심한 통증(7점 이상): ").append(q.getHasSeverePain() ? "있음" : "없음").append("\n");
        prompt.append("- 창백/식은땀: ").append(q.getHasPaleFaceOrColdSweat() ? "있음" : "없음").append("\n");
        prompt.append("- 마비 증상: ").append(q.getHasParalysis() ? "있음" : "없음").append("\n");
        prompt.append("- 경련/발작: ").append(q.getHasSeizure() ? "있음" : "없음").append("\n");
        prompt.append("- 고열(38.5도 이상): ").append(q.getHasHighFever() ? "있음" : "없음").append("\n");
        prompt.append("- 심한 외상: ").append(q.getHasSevereTrauma() ? "있음" : "없음").append("\n");
        prompt.append("- 알레르기 반응: ").append(q.getHasAllergicReaction() ? "있음" : "없음").append("\n");

        if (q.getAdditionalSymptoms() != null && !q.getAdditionalSymptoms().trim().isEmpty()) {
            prompt.append("- 추가 증상: ").append(q.getAdditionalSymptoms()).append("\n");
        }

        prompt.append("\n위 정보를 바탕으로 KTAS 등급을 분석하고 JSON 형태로 응답해주세요.");

        return prompt.toString();
    }

    private KtasResultDto parseAIResponse(String aiResponse) {
        try {
            // JSON 부분만 추출
            String jsonPart = extractJsonFromResponse(aiResponse);
            return objectMapper.readValue(jsonPart, KtasResultDto.class);
        } catch (Exception e) {
            return createDefaultResult();
        }
    }

    private String extractJsonFromResponse(String response) {
        // JSON 시작과 끝을 찾아서 추출
        int start = response.indexOf("{");
        int end = response.lastIndexOf("}") + 1;

        if (start >= 0 && end > start) {
            return response.substring(start, end);
        }

        return response;
    }

    private KtasResultDto validateAndAdjustResult(KtasResultDto result, KtasQuestionnaireDto q) {
        // 중요한 생명 징후 확인
        if (Boolean.FALSE.equals(q.getIsConsciousClear()) && result.getKtasLevel() > 2) {
            result.setKtasLevel(1);
            result.setUrgencyLevel("즉시 처치");
            result.setDescription("의식 저하로 인한 즉시 처치 필요");
        }

        if (Boolean.TRUE.equals(q.getHasBreathingDifficulty()) &&
                Boolean.TRUE.equals(q.getHasPaleFaceOrColdSweat()) &&
                result.getKtasLevel() > 1) {
            result.setKtasLevel(1);
            result.setUrgencyLevel("즉시 처치");
        }

        return result;
    }

    private KtasResultDto createFallbackResult(KtasQuestionnaireDto q) {
        // AI 분석 실패시 기본 규칙 기반 분석
        int level = 5;

        if (Boolean.FALSE.equals(q.getIsConsciousClear()) ||
                (Boolean.TRUE.equals(q.getHasBreathingDifficulty()) &&
                        Boolean.TRUE.equals(q.getHasPaleFaceOrColdSweat()))) {
            level = 1;
        } else if (Boolean.TRUE.equals(q.getHasParalysis()) ||
                Boolean.TRUE.equals(q.getHasAllergicReaction())) {
            level = 2;
        } else if (Boolean.TRUE.equals(q.getHasSeverePain()) ||
                Boolean.TRUE.equals(q.getHasSevereTrauma())) {
            level = 3;
        } else if (Boolean.TRUE.equals(q.getHasBleeding()) ||
                Boolean.TRUE.equals(q.getHasHighFever())) {
            level = 4;
        }

        return new KtasResultDto(level, getLevelDescription(level),
                "기본 규칙 기반 분석 결과", "의료진 상담 권장",
                "기본 분석", 0.7, "AI 분석 대체 결과");
    }

    private KtasResultDto createDefaultResult() {
        return new KtasResultDto(3, "긴급", "분석 오류로 인한 기본 등급",
                "즉시 의료진 상담", "분석 오류", 0.5, "분석 실패");
    }

    private String getLevelDescription(int level) {
        switch (level) {
            case 1: return "즉시 처치";
            case 2: return "15분 이내";
            case 3: return "30분 이내";
            case 4: return "60분 이내";
            case 5: return "120분 이내";
            default: return "분류 오류";
        }
    }
}
