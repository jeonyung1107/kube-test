package com.caffeinegorilla.kubetest.user.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "사용자"),
    ADMIN("ROLE_ADMIN", "어드민"),
    GUEST("ROLE_GUEST", "게스트")
    ;

    private final String role;
    private final String title;

}
