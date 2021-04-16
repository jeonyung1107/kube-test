package com.caffeinegorilla.kubetest.auth;

import com.caffeinegorilla.kubetest.exception.KubeException;
import com.caffeinegorilla.kubetest.user.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProcessor jwtTokenProcessor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenOptional = getToken(request);

        tokenOptional.ifPresent(token -> {
            if (jwtTokenProcessor.validate(token)){
                TokenBody parsed = jwtTokenProcessor.parse(token);
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(parsed.getRole()));
                UserPrincipal principal = new UserPrincipal(null,
                        parsed.getEmail(),
                        "",
                        authorities);

                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal,
                        token,
                        authorities));
            } else {
                throw new KubeException();
            }
        });

        filterChain.doFilter(request, response);
    }

    Optional<String> getToken(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("Authorization"));
    }
}
