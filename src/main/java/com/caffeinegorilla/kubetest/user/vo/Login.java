package com.caffeinegorilla.kubetest.user.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Login {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
