package com.server.d2ackserver.domain.patient.domain.entity;

import com.server.d2ackserver.domain.ai.dto.response.KtasResultDto;
import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import com.server.d2ackserver.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Setter
public class PatientEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String imageUrl;

    Integer ktasLevel;

    Boolean agree;

    @OneToOne(fetch = FetchType.EAGER)
    UserEntity agreedOrganic;
}
