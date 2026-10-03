package com.example.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Service
public class S3FileService {

	/*
	 * aws packages -> s3 (file upload, delete etc), auth aws credentials -> access
	 * key, secret key, region aws authenticate use s3 file upload method to upload
	 * file to s3 bucket
	 */

	@Value("${aws.accessKeyId}")
	private String accessKeyId;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Value("${aws.region}")
	private String region;

	@Value("${springbootappdatalocal-storage996}")
	private String bucketName;

	private S3Client s3Client;

	public S3FileService() {
		AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKeyId, accessKeyId);
		s3Client = S3Client.builder().region(Region.of(region))
				.credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials)).build();
	}

}
