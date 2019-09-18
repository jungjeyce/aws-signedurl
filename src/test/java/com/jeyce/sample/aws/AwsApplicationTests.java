package com.jeyce.sample.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfilesConfigFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.jeyce.sample.aws.service.AwsSignedUrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwsApplicationTests {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.region}")
    private String region;
    @Value("${bucket.name}")
    private String privateBucketName;

    @Autowired
    AwsSignedUrlService awsSignedUrlService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void signedUrlTest(){
        String signedUrl = awsSignedUrlService.getSignedDownloadUrl("contracts/testfile.png");
        System.out.println("Generating signed URL.==> "+ signedUrl);
    }
}
