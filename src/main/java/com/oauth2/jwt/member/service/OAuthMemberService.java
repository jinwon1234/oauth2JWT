package com.oauth2.jwt.member.service;

import com.oauth2.jwt.entity.OAuthMember;
import com.oauth2.jwt.member.dto.OAuthMemberDto;
import com.oauth2.jwt.member.dto.OAuthResponse;
import com.oauth2.jwt.member.repository.OAuthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class OAuthMemberService {

    private final OAuthMemberRepository memberRepository;

    public OAuthMemberDto save(String identifier, OAuthResponse oAuthResponse) {


        Optional<OAuthMember> findMember = memberRepository.findByIdentifier(identifier);

        if (findMember.isPresent()) {
            OAuthMember member = findMember.get();
            return new OAuthMemberDto(member);
        }

        OAuthMember saved = memberRepository.save(new OAuthMember(oAuthResponse.getName(), identifier, oAuthResponse.getEmail(), "ROLE_USER"));
        return new OAuthMemberDto(saved);
    }

}
