package com.server.d2ackserver.domain.user.domain.enitty;

import com.server.d2ackserver.domain.user.domain.enums.UserClass;
import com.server.d2ackserver.domain.user.domain.enums.UserField;
import com.server.d2ackserver.domain.user.domain.enums.UserProvider;
import com.server.d2ackserver.domain.user.domain.enums.UserRole;
import com.server.d2ackserver.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
    UserProvider provider;

    @Enumerated
    UserField field; //분야

    @Enumerated
    UserClass userClass; //직군

    @Enumerated
    UserRole role;
}
