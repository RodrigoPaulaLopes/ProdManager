package com.rodrigo.ProdManager.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {
    @Value("${aws.access_key_id}")
    private String awsId;
    @Value("${aws.secret_access_key}")
    private String awsKey;
    @Value("${s3.region}")
    private String region;

    @Bean
    public AmazonS3 s3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(awsId, awsKey);
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        return s3;
    }
}
