package com.caffeinegorilla.kubetest.user;

import com.caffeinegorilla.kubetest.auth.JwtTokenProcessor;
import com.caffeinegorilla.kubetest.auth.TokenBody;
import com.caffeinegorilla.kubetest.user.security.Role;
import com.caffeinegorilla.kubetest.user.vo.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProcessor jwtTokenProcessor;

    @Transactional
    public User join(User user) {

        User passwordEncoded = new User(null,
                user.getEmail(),
                user.getName(),
                passwordEncoder.encode(user.getPassword()),
                Role.USER);

        User result = userRepository.save(passwordEncoded);

        return result;
    }

    public String login(Login login){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );

        return jwtTokenProcessor.createToken(new TokenBody(login.getEmail(), "USER", 1649997667000L));
    }
}
