package com.oauth2.jwt.member.controller;

import com.oauth2.jwt.member.dto.CustomO2AuthClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/my")
    public String myAPI(@AuthenticationPrincipal CustomO2AuthClient client) {

        return client.getIdentifier();
    }
}
