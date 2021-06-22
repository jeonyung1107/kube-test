package com.caffeinegorilla.kubetest.controller;

import com.caffeinegorilla.kubetest.common.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Url.RANDOM_NUMBER)
public class KubeController {

    @GetMapping
    public Integer get(){
        return (int)(Math.random() * 100);
    }
}
