package com.server.d2ackserver.domain.user.domain.enums;

public enum UserClass {
    // 의료 (내과)
    emergencyMedicine, // 응급의학과
    criticalCareMedicine, // 중환자의학과
    cardiology, // 심장내과
    nephrology, // 신장내과
    pulmonology, // 호흡기내과
    neurology, // 신경과
    infectiousDiseases, // 감염내과
    pediatrics, // 소아청소년과
    geriatricMedicine, // 노인병내과
    allergyImmunology, // 알레르기내과
    emergencyNursing, // 응급 간호
    icuNursing, // 중환자실 간호
    erNursing, // 응급실 간호

    // 의료 (외과)
    traumaSurgery, // 외상외과
    neurosurgery, // 신경외과
    orthopedicSurgery, // 정형외과
    thoracicSurgery, // 흉부외과
    vascularSurgery, // 혈관외과 (정맥외과)
    plasticSurgery, // 성형외과
    generalSurgery, // 일반외과
    otorhinolaryngology, // 이비인후과
    ophthalmology, // 안과
    burnSurgery, // 화상외과
    oralMaxillofacialSurgery, // 구강악안면외과
    urology, // 비뇨의학과
    traumaNursing, // 외상전문의 간호

    // 소방 행정, 지휘
    fireCommander, // 소방 지휘관
    firefighter, // 소방관
    fireHelicopterPilot, // 소방 헬기 조종사
    fireDispatchCenter, // 119 상황실
    emergencyTransportCoordinator, // 응급이송 코디네이터

    // 소방 구조
    fireRescueTeam, // 소방 구조대
    waterRescueTeam, // 수난 구조대
    highwayRescueTeam, // 고속도로 구조대
    fireRescueUnit, // 화재 구조대
    specialRescueTeam, // 특수 구조대

    // 일반 구조
    policeSwatRescue, // 경찰 특공대 구조
    disasterResponseUnit, // 재난 대응 특수부대
    mountainRescueTeam, // 산악 구조대
    coastGuardRescueTeam, // 해양경찰 구조대
    militaryRescueTeam, // 군 구조대

    // 응급 구조, 이송
    emsUnit, // 119 구급대
    emergencyMedicTech1, // 응급구조 1급
    emergencyMedicTech2, // 응급구조 2급
    hospitalEmergencyMedic // 병원 응급구조

}
