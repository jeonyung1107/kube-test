package com.caffeinegorilla.kubetest.controller.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CreateUser {

    @NotBlank
    private String name;
}
