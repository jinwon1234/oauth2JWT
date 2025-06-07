package com.oauth2.jwt.annotation;

import com.oauth2.jwt.member.dto.CustomO2AuthClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


public class WithCustomSecurityContextFactory
    implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {

        String identifier = annotation.identifier();
        String username = annotation.username();
        String role = annotation.role();

        CustomO2AuthClient customO2AuthClient = new CustomO2AuthClient(identifier, username, role);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(customO2AuthClient, null, customO2AuthClient.getAuthorities()));


        return securityContext;
    }
}
