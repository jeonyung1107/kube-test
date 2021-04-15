package com.caffeinegorilla.kubetest.controller;

import com.caffeinegorilla.kubetest.controller.request.user.CreateUser;
import com.caffeinegorilla.kubetest.user.User;
import com.caffeinegorilla.kubetest.user.UserService;
import com.caffeinegorilla.kubetest.user.security.Role;
import com.caffeinegorilla.kubetest.user.vo.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUser createUser){
        User created = userService.join(new User(null,
                createUser.getEmail(),
                createUser.getName(),
                createUser.getPassword(),
                Role.USER));

        return ResponseEntity.ok(created);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> login(@Valid @RequestBody Login login){
        String token = userService.login(login);

        return ResponseEntity.ok(token);
    }
}
