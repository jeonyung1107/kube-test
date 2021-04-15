package com.caffeinegorilla.kubetest.user;

import com.caffeinegorilla.kubetest.common.Constants;
import com.caffeinegorilla.kubetest.user.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Entity
@EqualsAndHashCode
@Builder
@Table(name = Constants.Table.USER)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    private String name;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
