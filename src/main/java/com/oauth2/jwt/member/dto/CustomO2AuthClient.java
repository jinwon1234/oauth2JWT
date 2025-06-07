package com.oauth2.jwt.member.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomO2AuthClient implements OAuth2User {


    private final String role;
    private final String username;
    private final String identifier;

    public CustomO2AuthClient(OAuthMemberDto memberDto) {
        this.role = memberDto.getRole();
        this.username = memberDto.getName();
        this.identifier = memberDto.getIdentifier();
    }

    public CustomO2AuthClient(String role, String username, String identifier) {
        this.role = role;
        this.username = username;
        this.identifier = identifier;
    }

    @Override
    public Map<String, Object> getAttributes() {
        // naver, google 등 소셜마다 제공하는 attribute 형식이 다르기 때문에 사용하지 않는다.
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return this.username;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
