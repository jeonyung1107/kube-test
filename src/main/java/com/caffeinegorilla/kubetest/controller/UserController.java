package com.caffeinegorilla.kubetest.controller;

import com.caffeinegorilla.kubetest.controller.request.user.CreateUser;
import com.caffeinegorilla.kubetest.persistence.user.User;
import com.caffeinegorilla.kubetest.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public User createUser(@Valid @RequestBody CreateUser createUser){
        User user = new User(createUser.getName());
        User result = userRepository.save(user);

        return result;
    }
}
