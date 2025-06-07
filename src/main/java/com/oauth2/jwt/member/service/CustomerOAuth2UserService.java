package com.oauth2.jwt.member.service;


import com.oauth2.jwt.entity.OAuthMember;
import com.oauth2.jwt.member.dto.*;
import com.oauth2.jwt.member.repository.OAuthMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerOAuth2UserService extends DefaultOAuth2UserService {

    private final OAuthMemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("OAuth2User : {}", oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuthResponse oAuthResponse;

        if ("naver".equals(registrationId)) {
            oAuthResponse = new NaverResponse(oAuth2User.getAttributes());

        } else if ("google".equals(registrationId)) {
            oAuthResponse = new GoogleResponse(oAuth2User.getAttributes());
        }
        else if ("kakao".equals(registrationId)) {
            oAuthResponse = new KakaoResponse(oAuth2User.getAttributes());
        }
        else return null;

        String identifier  = oAuthResponse.getProvider() + "." + oAuthResponse.getProviderId();


        OAuthMemberDto memberDto = memberService.save(identifier, oAuthResponse);

        return new CustomO2AuthClient(memberDto);
    }
}
