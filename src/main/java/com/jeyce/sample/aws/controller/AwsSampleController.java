package com.jeyce.sample.aws.controller;

import com.jeyce.sample.aws.service.AwsSignedUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/sample")
public class AwsSampleController {

    @Autowired
    AwsSignedUrlService awsSignedUrlService;


    @GetMapping("/download")
    public RedirectView getDownload(@RequestParam  String filename){
        String redirectUrl;
        redirectUrl = awsSignedUrlService.getSignedDownloadUrl(filename);
        return new RedirectView(redirectUrl);
    }

    @GetMapping("/downloadurl")
    public String getUrlDownload(@RequestParam String filename){
        String redirectUrl;
        redirectUrl = awsSignedUrlService.getSignedDownloadUrl(filename);
        return redirectUrl;
    }
}
