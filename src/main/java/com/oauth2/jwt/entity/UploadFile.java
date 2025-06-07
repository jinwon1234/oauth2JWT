package com.oauth2.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Member;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String serverFileName;

    @Column(nullable = false, length = 65535)
    @Lob
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private OAuthMember member;

    public UploadFile(String fileName, String serverFileName, String url, OAuthMember member) {
        this.fileName = fileName;
        this.serverFileName = serverFileName;
        this.url = url;
        this.member = member;
    }


}