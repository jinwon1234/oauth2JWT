package com.oauth2.jwt.member.dto;

import java.util.Map;

public class KakaoResponse implements OAuthResponse{

    private final Map<String, Object> attributes;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {

        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> infoMap = (Map<String, Object>) attributes.get("kakao_account");

        if (infoMap.containsKey("email")) return infoMap.get("email").toString();
        return null;
    }

    @Override
    public String getName() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        if (kakaoAccount != null) {
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            if (profile != null) {
                return (String) profile.get("nickname");
            }
        }
        return null;
    }
}
