package com.oauth2.jwt.member.dto;

import com.oauth2.jwt.entity.OAuthMember;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthMemberDto {

    private String Identifier;
    private String name;
    private String role;
    private String email;

    public OAuthMemberDto(String Identifier, String role) {
        this.Identifier = Identifier;
        this.role = role;
    }

    public OAuthMemberDto(OAuthMember member) {
        this.Identifier = member.getIdentifier();
        this.name = member.getUsername();
        this.role = member.getRole();
        this.email = member.getEmail();
    }


}
