package com.example.demo.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploaderService {

	// get file type extension
	// check if it is in allowed file types -> if yes ok else throw error
	// check file size -> if its under allowed size ok else throw error
	// generate unique file name
	// upload to folder

	private int MAX_ALLOWED_IMAGE_SIZE = 5 * 1024 * 1024;

	public void handleFileUpload(MultipartFile inputFile) throws Exception {
		String fileName = StringUtils.cleanPath(inputFile.getOriginalFilename());
		String fileType = StringUtils.getFilenameExtension(inputFile.getOriginalFilename());

		String[] allowedFileTypes = { "jpg", "png", "gif", "jpeg" };
		Boolean isFileTypeAllowed = Arrays.stream(allowedFileTypes).anyMatch(fileType::equals);

		if (isFileTypeAllowed == false) {
			throw new Exception("file type is not allowed");
		}

		System.out.println("file size " + inputFile.getSize());
		System.out.println("max size " + MAX_ALLOWED_IMAGE_SIZE);

	}
}
