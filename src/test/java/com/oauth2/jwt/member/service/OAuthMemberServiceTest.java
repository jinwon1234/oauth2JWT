package com.oauth2.jwt.member.service;

import com.oauth2.jwt.entity.OAuthMember;
import com.oauth2.jwt.member.dto.OAuthMemberDto;
import com.oauth2.jwt.member.dto.OAuthResponse;
import com.oauth2.jwt.member.repository.OAuthMemberRepository;
import com.oauth2.jwt.testcase.MemberCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OAuthMemberServiceTest {

    @InjectMocks
    private OAuthMemberService oAuthMemberService;

    @Mock
    private OAuthMemberRepository memberRepository;

    @Test
    @DisplayName("기존 회원이 존재하지 않으면 회원가입")
    void createMember1() {

        // given
        OAuthMember member = MemberCase.createMember();
        OAuthResponse response = MemberCase.createResponse();

        given(memberRepository.findByIdentifier(member.getIdentifier()))
                .willReturn(Optional.empty());

        given(memberRepository.save(any(OAuthMember.class)))
                .willReturn(member);

        // when
        OAuthMemberDto save = oAuthMemberService.save(member.getIdentifier(), response);

        //then
        verify(memberRepository).save(any(OAuthMember.class));
        assertThat(save.getIdentifier()).isEqualTo(member.getIdentifier());
        assertThat(save.getEmail()).isEqualTo(member.getEmail());
        assertThat(save.getName()).isEqualTo(member.getUsername());
        assertThat(save.getRole()).isEqualTo(member.getRole());
    }

    @Test
    @DisplayName("기존 회원이 존재하면 기존 회원의 DTO를 반환")
    void createMember2() {

        // given
        OAuthMember member = MemberCase.createMember();
        OAuthResponse response = MemberCase.createResponse();

        given(memberRepository.findByIdentifier(member.getIdentifier()))
                .willReturn(Optional.of(member));

        // when
        OAuthMemberDto save = oAuthMemberService.save(member.getIdentifier(), response);

        // then
        verify(memberRepository).findByIdentifier(member.getIdentifier());
        verifyNoMoreInteractions(memberRepository);
        assertThat(save.getIdentifier()).isEqualTo(member.getIdentifier());
        assertThat(save.getEmail()).isEqualTo(member.getEmail());
        assertThat(save.getName()).isEqualTo(member.getUsername());
        assertThat(save.getRole()).isEqualTo(member.getRole());
    }

}