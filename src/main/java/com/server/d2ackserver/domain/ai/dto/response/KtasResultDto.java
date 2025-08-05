package com.server.d2ackserver.domain.ai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KtasResultDto {
    @JsonProperty("ktasLevel")
    private Integer ktasLevel;      // KTAS 등급 (1-5)

    @JsonProperty("urgencyLevel")
    private String urgencyLevel;    // 응급도 설명

    @JsonProperty("description")
    private String description;     // 상세 설명

    @JsonProperty("recommendations")
    private String recommendations; // 권장 사항

    @JsonProperty("riskFactors")
    private String riskFactors;     // 위험 요인

    @JsonProperty("confidence")
    private Double confidence;      // AI 신뢰도 (0-1)

    @JsonProperty("aiAnalysis")
    private String aiAnalysis;      // AI 분석 상세 내용
}
