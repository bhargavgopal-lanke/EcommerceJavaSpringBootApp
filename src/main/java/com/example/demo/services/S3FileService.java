package com.example.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

}
