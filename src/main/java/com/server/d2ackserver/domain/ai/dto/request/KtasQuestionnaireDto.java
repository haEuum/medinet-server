package com.server.d2ackserver.domain.ai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KtasQuestionnaireDto {
    @JsonProperty("patientAge")
    private Integer patientAge;           // 환자 나이

    @JsonProperty("isConsciousClear")
    private Boolean isConsciousClear;     // 의식 명확 여부

    @JsonProperty("hasBreathingDifficulty")
    private Boolean hasBreathingDifficulty; // 호흡곤란 여부

    @JsonProperty("hasBleeding")
    private Boolean hasBleeding;          // 출혈 여부

    @JsonProperty("hasSeverePain")
    private Boolean hasSeverePain;        // 심한 통증 여부 (7점 이상)

    @JsonProperty("hasPaleFaceOrColdSweat")
    private Boolean hasPaleFaceOrColdSweat; // 창백하거나 식은땀 여부

    @JsonProperty("hasParalysis")
    private Boolean hasParalysis;         // 마비 여부

    @JsonProperty("hasSeizure")
    private Boolean hasSeizure;           // 경련/발작 여부

    @JsonProperty("hasHighFever")
    private Boolean hasHighFever;         // 고열 여부 (38.5도 이상)

    @JsonProperty("hasSevereTrauma")
    private Boolean hasSevereTrauma;      // 심한 외상 여부

    @JsonProperty("hasAllergicReaction")
    private Boolean hasAllergicReaction;  // 알레르기 반응 여부

    // 추가 정보 (선택사항)
    @JsonProperty("additionalSymptoms")
    private String additionalSymptoms;    // 추가 증상 설명
}
