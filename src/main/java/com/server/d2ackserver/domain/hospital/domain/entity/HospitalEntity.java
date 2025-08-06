package com.server.d2ackserver.domain.hospital.domain.entity;

import com.server.d2ackserver.domain.user.domain.enitty.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class HospitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String password;
    String address;
    String telephone;

    @OneToMany
    List<UserEntity> duties = new ArrayList<>();
}
