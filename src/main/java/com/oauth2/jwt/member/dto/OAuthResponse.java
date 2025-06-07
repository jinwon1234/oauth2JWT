package com.oauth2.jwt.member.dto;

public interface OAuthResponse {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
