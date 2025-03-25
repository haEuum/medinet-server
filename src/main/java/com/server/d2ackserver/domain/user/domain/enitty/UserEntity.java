package com.server.d2ackserver.domain.user.domain.enitty;

import com.server.d2ackserver.domain.user.domain.enums.UserClass;
import com.server.d2ackserver.domain.user.domain.enums.UserField;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;
import com.server.d2ackserver.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    String password;

    @Enumerated(EnumType.STRING)
    UserProvider provider; // OAuth 제공자

    @Column(nullable = false, unique = true)
    Long biometricAuthNum;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Length(max = 11, min = 11)
    String phoneNumber; // 전화번호

    @Enumerated(EnumType.STRING)
    UserField field; //분야

    @Enumerated(EnumType.STRING)
    UserClass userClass; //직군

    @Enumerated(EnumType.STRING)
    UserRole role;
}
