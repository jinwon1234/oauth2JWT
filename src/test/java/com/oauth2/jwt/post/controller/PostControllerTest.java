package com.oauth2.jwt.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth2.jwt.annotation.WithMockCustomUser;
import com.oauth2.jwt.entity.OAuthMember;
import com.oauth2.jwt.post.dto.PostRequestDto;
import com.oauth2.jwt.post.dto.PostResponseDto;
import com.oauth2.jwt.post.service.PostService;
import com.oauth2.jwt.testcase.MemberCase;
import com.oauth2.jwt.testcase.PostCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockitoBean
    PostService postService;


    @WithMockCustomUser
    @Test
    @DisplayName("게시글 작성 성공")
    void createPostTest1() throws Exception {
        PostRequestDto postRequestDto = PostCase.buildPostRequestDto();
        String content = objectMapper.writeValueAsString(postRequestDto);
        OAuthMember member = MemberCase.createMember();
        PostResponseDto postResponseDto = new PostResponseDto("1234", postRequestDto.getTitle(), postRequestDto.getContent(), member.getUsername());


        given(postService.createPost(any(PostRequestDto.class), any(String.class)))
                .willReturn(postResponseDto);

        mockMvc.perform(post("/posts").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.postId").value(postResponseDto.getPostId()))
                .andExpect(jsonPath("$.title").value(postResponseDto.getTitle()))
                .andExpect(jsonPath("$.content").value(postResponseDto.getContent()))
                .andExpect(jsonPath("$.memberName").value(postResponseDto.getMemberName()));


        verify(postService).createPost(any(PostRequestDto.class), any(String.class));
    }

    @Test
    @WithMockCustomUser
    @DisplayName("게시글 작성 실패")
    void createPostTest2() throws Exception {

        PostRequestDto postRequestDto = new PostRequestDto("1", "22");

        String content = objectMapper.writeValueAsString(postRequestDto);

        mockMvc.perform(post("/posts").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fieldErrors").isNotEmpty())
                .andExpect(jsonPath("$.globalErrors").isEmpty());

        verifyNoMoreInteractions(postService);

    }
}