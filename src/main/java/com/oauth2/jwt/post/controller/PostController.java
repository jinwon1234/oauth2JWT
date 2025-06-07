package com.oauth2.jwt.post.controller;

import com.oauth2.jwt.member.dto.CustomO2AuthClient;
import com.oauth2.jwt.post.dto.PostRequestDto;
import com.oauth2.jwt.post.dto.PostResponseDto;
import com.oauth2.jwt.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> savePost(@Valid @RequestBody PostRequestDto postRequestDto,
                                                    @AuthenticationPrincipal CustomO2AuthClient member) {

        PostResponseDto response = postService.createPost(postRequestDto, member.getIdentifier());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
