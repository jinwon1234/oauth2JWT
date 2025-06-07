package com.oauth2.jwt.oauth2;

import com.oauth2.jwt.member.dto.CustomO2AuthClient;
import com.oauth2.jwt.member.dto.OAuthMemberDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        String accessToken = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("Authorization"))
                .map(cookie -> cookie.getValue())
                .findFirst()
                .orElse(null);

        if (accessToken == null) {
            response.getWriter().write("Access Token이 존재하지 않음");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (jwtUtil.isExpired(accessToken)) {
            response.getWriter().write("Access Token 만료");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String identifier = jwtUtil.getIdentifier(accessToken);
        String role = jwtUtil.getRole(accessToken);

        OAuthMemberDto oAuthMemberDto = new OAuthMemberDto(identifier, role);

        CustomO2AuthClient customO2AuthClient = new CustomO2AuthClient(oAuthMemberDto);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customO2AuthClient, null, customO2AuthClient.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("JWT token: {}", accessToken);

        filterChain.doFilter(request,response);
    }
}
