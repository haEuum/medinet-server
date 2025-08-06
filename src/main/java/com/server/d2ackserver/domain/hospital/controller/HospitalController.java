package com.server.d2ackserver.domain.hospital.controller;

import com.server.d2ackserver.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
}
