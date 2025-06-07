package com.oauth2.jwt.testcase;

import com.oauth2.jwt.entity.OAuthMember;
import com.oauth2.jwt.member.dto.KakaoResponse;
import com.oauth2.jwt.member.dto.OAuthResponse;
import jakarta.servlet.http.Cookie;

public abstract class MemberCase {

    public static OAuthMember createMember() {
        return new OAuthMember("유저이름", "kakao.1234","ljw0626@naver.com", "ROLE_USER");
    }

    public static OAuthResponse createResponse() {
        return new OAuthResponse() {
            @Override
            public String getProvider() {
                return "kakao";
            }

            @Override
            public String getProviderId() {
                return "1234";
            }

            @Override
            public String getEmail() {
                return "ljw0626@naver.com";
            }

            @Override
            public String getName() {
                return "유저이름";
            }
        };
    }

    public static Cookie createAccessToken(String token) {
        Cookie cookie = new Cookie("Authorization",token);
        cookie.setMaxAge(60*60*60);
        cookie.setPath("/"); // 모든 경로에서 쿠키 사용 가능
        cookie.setHttpOnly(true); // 자바스크립트로 쿠키 탈취 방지

        return cookie;
    }
}
