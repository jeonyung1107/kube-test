package com.caffeinegorilla.kubetest.auth;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class TokenBody {
    private String email;
    private Long exp;

    public Map<String, Object> toMap(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", this.email);
        claims.put("exp", this.exp);

        return claims;
    }
}
