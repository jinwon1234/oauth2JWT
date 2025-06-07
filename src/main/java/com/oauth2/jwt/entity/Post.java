package com.oauth2.jwt.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    private String id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private OAuthMember member;

    public Post(String title, String content, OAuthMember member) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
