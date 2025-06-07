package com.oauth2.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthMember {

    @Id
    private String id;

    private String username;

    private String email;

    private String identifier;

    private String role;

    public OAuthMember(String username, String identifier, String email, String role) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.identifier = identifier;
        this.email = email;
        this.role = role;
    }
}
