package com.oauth2.jwt.oauth2;

import com.oauth2.jwt.member.dto.CustomO2AuthClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomO2AuthClient verifiedClient = (CustomO2AuthClient) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = verifiedClient.getAuthorities();
        GrantedAuthority auth = authorities.iterator().next();
        String authority = auth.getAuthority();
        String accessToken = jwtUtil.createJWT(verifiedClient.getIdentifier(), authority, 60*60*60L);

        setAuthCookie(response, accessToken);
        response.sendRedirect("http://www.jinwon.click");
    }

    private void setAuthCookie(HttpServletResponse response, String token) {
        String cookieValue = "Authorization=" + token +
                "; Path=/" +
                "; Max-Age=" + (60 * 60 * 60) +
                "; HttpOnly" +
                "; Secure" +
                "; SameSite=None";

        response.setHeader("Set-Cookie", cookieValue);
    }
}
