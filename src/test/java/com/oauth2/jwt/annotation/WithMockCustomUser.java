package com.oauth2.jwt.annotation;


import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(
        factory = WithCustomSecurityContextFactory.class
)
public @interface WithMockCustomUser {

    String identifier() default "kakao.1234";
    String username() default "유저이름";
    String role() default "ROLE_USER";
}
