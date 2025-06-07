package com.oauth2.jwt.fileupload.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.oauth2.jwt.entity.UploadFile;
import com.oauth2.jwt.fileupload.dto.UploadFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class UploadService {

    private final AmazonS3Client s3Client;

    @Value("${bucketName}")
    private String bucket;

    public UploadFileDto getPresignedURL(String fileName) {

        String serverFileName = changeFileName(fileName);
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 5; // 5분
        expiration.setTime(expTimeMillis);
        String url = s3Client.generatePresignedUrl(bucket, serverFileName, expiration, HttpMethod.PUT).toString();


        return new UploadFileDto(fileName, serverFileName, url);
    }

    public void deleteFiles(List<UploadFile> files) {
        for (UploadFile file : files) {
            s3Client.deleteObject(bucket, file.getServerFileName());
        }
    }


    private String changeFileName(String originalFileName) {
        String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        return uuid + ext;
    }
}
