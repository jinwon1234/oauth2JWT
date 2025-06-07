package com.oauth2.jwt.member.repository;

import com.oauth2.jwt.entity.OAuthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OAuthMemberRepository extends JpaRepository<OAuthMember, String> {

    Optional<OAuthMember> findByIdentifier(String identifier);

}
