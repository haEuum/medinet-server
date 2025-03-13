package com.server.d2ackserver.domain.user.domain.enitty;

import com.server.d2ackserver.domain.user.domain.enums.UserClass;
import com.server.d2ackserver.domain.user.domain.enums.UserField;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;
import com.server.d2ackserver.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @NotBlank
    @Column(nullable = false)
    String name;

    String password;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Email(message = "email wrong")
    String email;

    @Enumerated
    UserProvider provider; // OAuth 제공자

    @Column(nullable = false, unique = true)
    Long biometricAuthNum;

    @Length(max = 11, min = 11)
    String phoneNumber; // 전화번호

    @Enumerated
    UserField field; //분야

    @Enumerated
    UserClass userClass; //직군

    @Enumerated
    UserRole role;
}
