package com.server.d2ackserver.domain.patient.dto.request;

import com.server.d2ackserver.domain.ai.dto.request.KtasQuestionnaireDto;

public record SendPatientRequest(
        String name,
        KtasQuestionnaireDto questions
) {
}
