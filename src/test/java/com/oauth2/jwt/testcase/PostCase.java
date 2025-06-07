package com.oauth2.jwt.testcase;

import com.oauth2.jwt.entity.Post;
import com.oauth2.jwt.post.dto.PostRequestDto;

public abstract class PostCase {

    public static PostRequestDto buildPostRequestDto() {
        return new PostRequestDto("제목1", "내용1");
    }

}
