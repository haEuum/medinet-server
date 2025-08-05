package com.server.d2ackserver.domain.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.d2ackserver.domain.ai.dto.request.OpenAIRequest;
import com.server.d2ackserver.domain.ai.dto.response.OpenAIResponse;
import com.server.d2ackserver.global.config.OpenAIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class OpenAIService {

    @Autowired
    private OpenAIConfig openAIConfig;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String callOpenAI(String prompt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openAIConfig.getApiKey());

            OpenAIRequest request = new OpenAIRequest();
            request.setMessages(Arrays.asList(
                    new OpenAIRequest.Message("system", getSystemPrompt()),
                    new OpenAIRequest.Message("user", prompt)
            ));

            HttpEntity<OpenAIRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<OpenAIResponse> response = restTemplate.exchange(
                    openAIConfig.getApiUrl(),
                    HttpMethod.POST,
                    entity,
                    OpenAIResponse.class
            );

            if (response.getBody() != null &&
                    response.getBody().getChoices() != null &&
                    !response.getBody().getChoices().isEmpty()) {
                return response.getBody().getChoices().get(0).getMessage().getContent();
            }

            return "AI 분석 중 오류가 발생했습니다.";

        } catch (Exception e) {
            e.printStackTrace();
            return "OpenAI API 호출 중 오류가 발생했습니다: " + e.getMessage();
        }
    }

    private String getSystemPrompt() {
        return """
            당신은 한국의 응급의학 전문의입니다. 
            한국형 응급환자 분류도구(KTAS) 기준에 따라 환자의 응급도를 정확히 분류해야 합니다.
            
            KTAS 분류 기준:
            - 1단계 (즉시): 생명을 위협하는 상황, 즉시 처치 필요
            - 2단계 (응급): 잠재적 생명위험, 15분 이내 처치
            - 3단계 (긴급): 응급상황이지만 생명위험 낮음, 30분 이내
            - 4단계 (준응급): 덜 급한 상황, 60분 이내
            - 5단계 (비응급): 비응급 상황, 120분 이내
            
            다음 형식으로 JSON 응답해주세요:
            {
                "ktasLevel": 숫자,
                "urgencyLevel": "응급도 설명",
                "description": "상세 설명",
                "recommendations": "권장 사항",
                "riskFactors": "주요 위험 요인",
                "confidence": 0.0~1.0 신뢰도,
                "aiAnalysis": "AI 분석 상세 내용"
            }
            
            의학적 근거를 바탕으로 정확하고 신중하게 분석해주세요.
            """;
    }
}
