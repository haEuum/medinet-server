package com.server.d2ackserver.domain.user.domain.enums;

public enum UserClass {
    // 의료 (내과)
    EMERGENCY_MEDICINE, // 응급의학과
    CRITICAL_CARE_MEDICINE, // 중환자의학과
    CARDIOLOGY, // 심장내과
    NEPHROLOGY, // 신장내과
    PULMONOLOGY, // 호흡기내과
    NEUROLOGY, // 신경과
    INFECTIOUS_DISEASES, // 감염내과
    PEDIATRICS, // 소아청소년과
    GERIATRIC_MEDICINE, // 노인병내과
    ALLERGY_IMMUNOLOGY, // 알레르기내과
    EMERGENCY_NURSING, // 응급 간호
    ICU_NURSING, // 중환자실 간호
    ER_NURSING, // 응급실 간호

    // 의료 (외과)
    TRAUMA_SURGERY, // 외상외과
    NEUROSURGERY, // 신경외과
    ORTHOPEDIC_SURGERY, // 정형외과
    THORACIC_SURGERY, // 흉부외과
    VASCULAR_SURGERY, // 혈관외과 (정맥외과)
    PLASTIC_SURGERY, // 성형외과
    GENERAL_SURGERY, // 일반외과
    OTORHINOLARYNGOLOGY, // 이비인후과
    OPHTHALMOLOGY, // 안과
    BURN_SURGERY, // 화상외과
    ORAL_MAXILLOFACIAL_SURGERY, // 구강악안면외과
    UROLOGY, // 비뇨의학과
    TRAUMA_NURSING, // 외상전문의 간호

    // 소방 행정, 지휘
    FIRE_COMMANDER, // 소방 지휘관
    FIREFIGHTER, // 소방관
    FIRE_HELICOPTER_PILOT, // 소방 헬기 조종사
    FIRE_DISPATCH_CENTER, // 119 상황실
    EMERGENCY_TRANSPORT_COORDINATOR, // 응급이송 코디네이터

    // 소방 구조
    FIRE_RESCUE_TEAM, // 소방 구조대
    WATER_RESCUE_TEAM, // 수난 구조대
    HIGHWAY_RESCUE_TEAM, // 고속도로 구조대
    FIRE_RESCUE_UNIT, // 화재 구조대
    SPECIAL_RESCUE_TEAM, // 특수 구조대

    // 일반 구조
    POLICE_SWAT_RESCUE, // 경찰 특공대 구조
    DISASTER_RESPONSE_UNIT, // 재난 대응 특수부대
    MOUNTAIN_RESCUE_TEAM, // 산악 구조대
    COAST_GUARD_RESCUE_TEAM, // 해양경찰 구조대
    MILITARY_RESCUE_TEAM, // 군 구조대

    // 응급 구조, 이송
    EMS_UNIT, // 119 구급대
    EMERGENCY_MEDIC_TECH1, // 응급구조 1급
    EMERGENCY_MEDIC_TECH2, // 응급구조 2급
    HOSPITAL_EMERGENCY_MEDIC // 병원 응급구조
}
