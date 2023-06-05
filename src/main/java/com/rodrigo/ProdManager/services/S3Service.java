package com.rodrigo.ProdManager.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {
    @Value("${s3.bucket}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;


    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String filename = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();

            return uploadFile(is, filename, contentType);

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public URI uploadFile(InputStream is, String filename, String contentType) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);

            s3Client.putObject(bucketName, filename, is, objectMetadata);

            return s3Client.getUrl(bucketName, filename).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
