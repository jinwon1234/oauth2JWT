package com.oauth2.jwt.post.service;

import com.oauth2.jwt.entity.OAuthMember;
import com.oauth2.jwt.entity.Post;
import com.oauth2.jwt.exception.MemberNotFoundException;
import com.oauth2.jwt.member.repository.OAuthMemberRepository;
import com.oauth2.jwt.post.dto.PostRequestDto;
import com.oauth2.jwt.post.dto.PostResponseDto;
import com.oauth2.jwt.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final OAuthMemberRepository memberRepository;

    public PostResponseDto createPost(PostRequestDto postRequestDto, String memberIdentifier) {


        OAuthMember member = memberRepository.findByIdentifier(memberIdentifier).
                orElseThrow(() -> new MemberNotFoundException(memberIdentifier));

        Post save = postRepository.save(new Post(postRequestDto.getTitle(),
                postRequestDto.getContent(), member));

        return new PostResponseDto(save.getId(), save.getTitle(), save.getContent(), save.getMember().getUsername());
    }
}
