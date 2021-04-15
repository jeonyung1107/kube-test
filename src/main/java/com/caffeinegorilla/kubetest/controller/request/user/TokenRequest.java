package com.caffeinegorilla.kubetest.controller.request.user;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TokenRequest {
    @NotBlank
    private String token;

    public void setToken(String token){
        if (this.token == null){
            this.token = token;
        }
    }
}
