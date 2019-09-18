package com.jeyce.sample.aws.service;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.jeyce.sample.aws.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsSignedUrlService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${bucket.name}")
    private String privateBucketName;

    public String getSignedDownloadUrl(String filename) {
        return generateSignedUrl(filename);
    }

    private String generateSignedUrl(String filename) {
        String ret = null;
        String uniqueId = IDGenerator.next();

        // 서명URL 만료시간 설정(기본 10초)
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + 1000 *60 );
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(privateBucketName, filename);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        generatePresignedUrlRequest.setExpiration(expiration);

        // respose header 조작 ( 다운로드 파일 이름 변경이 필요한 경우 )
        ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
        // TODO : extension 작업 필요
        responseHeaders.setContentDisposition("attachment; filename=" + uniqueId);
        responseHeaders.setCacheControl("No-cache");
        generatePresignedUrlRequest.setResponseHeaders(responseHeaders);

        // URL 생성
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        ret = url.toString();

        return ret;
    }
}
