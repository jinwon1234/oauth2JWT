package com.oauth2.jwt.post.service;

import com.oauth2.jwt.entity.OAuthMember;
import com.oauth2.jwt.entity.Post;
import com.oauth2.jwt.exception.MemberNotFoundException;
import com.oauth2.jwt.member.repository.OAuthMemberRepository;
import com.oauth2.jwt.post.dto.PostRequestDto;
import com.oauth2.jwt.post.dto.PostResponseDto;
import com.oauth2.jwt.post.repository.PostRepository;

import com.oauth2.jwt.testcase.MemberCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private OAuthMemberRepository memberRepository;


    @Test
    @DisplayName("게시글 작성 성공 로직")
    void createTest1() {
        // given
        PostRequestDto postRequestDto = new PostRequestDto("제목1", "내용1");
        OAuthMember member = MemberCase.createMember();
        Post savedPost = new Post("제목1", "내용1", member);

        given(memberRepository.findByIdentifier(member.getIdentifier()))
                .willReturn(Optional.of(member));

        given(postRepository.save(any(Post.class)))
                .willReturn(savedPost);
        // when
        PostResponseDto responseDto = postService.createPost(postRequestDto, member.getIdentifier());

        // then
        verify(postRepository).save(any(Post.class));
        assertThat(responseDto.getPostId()).isEqualTo(savedPost.getId());
    }

    @Test
    @DisplayName("등록되지 않은 회원이 게시글 등록")
    void createTest2() {
        // given
        PostRequestDto postRequestDto = new PostRequestDto("제목1", "내용1");

        given(memberRepository.findByIdentifier("invalid"))
                .willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(()->postService.createPost(postRequestDto, "invalid"))
                .isInstanceOf(MemberNotFoundException.class);
    }

}