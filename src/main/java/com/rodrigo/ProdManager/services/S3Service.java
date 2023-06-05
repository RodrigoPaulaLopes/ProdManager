package com.rodrigo.ProdManager.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {
    @Value("${s3.bucket}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;


    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            s3Client.putObject(new PutObjectRequest(bucketName, "teste", file));
        } catch (AmazonServiceException e) {
            System.out.println("Ocorreu um erro -------------------> " + e.getErrorMessage());
        }catch (AmazonClientException e){
            System.out.println("Ocorreu um erro -------------------> " + e.getMessage());
        }
    }
}
