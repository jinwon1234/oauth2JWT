package com.oauth2.jwt.fileupload.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFileDto {

    private String fileName;

    private String serverFileName;

    private String url;
}
