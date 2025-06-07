package com.oauth2.jwt.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {

    @Size(min = 2, max = 20, message = "제목은 2글자 이상 20글자 이하")
    private String title;

    @Size(min = 3, message = "본문은 3글자 이상")
    private String content;


}

