package com.example.demo.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${file.upload.images.path}")
	private String imageUploadPath;

	private int MAX_ALLOWED_IMAGE_SIZE = 5 * 1024 * 1024;

	public void handleFileUpload(MultipartFile inputFile) throws Exception {
		String fileName = StringUtils.cleanPath(inputFile.getOriginalFilename());
		String fileType = StringUtils.getFilenameExtension(fileName);

		String[] allowedFileTypes = { "jpg", "png", "gif", "jpeg" };
		Boolean isFileTypeAllowed = Arrays.stream(allowedFileTypes).anyMatch(fileType::equals);

		if (isFileTypeAllowed == false) {
			throw new Exception(fileType + "file type is not allowed");
		}

		System.out.println("file size " + inputFile.getSize());
		System.out.println("max size " + MAX_ALLOWED_IMAGE_SIZE);
		System.out.println(imageUploadPath);

		if (inputFile.getSize() > MAX_ALLOWED_IMAGE_SIZE) {
			throw new Exception("Max 5mb Allowed");
		}

		String uploadImageName = UUID.randomUUID().toString() + "." + fileType;
		System.out.println(uploadImageName);

		Path uploadPath = Paths.get(imageUploadPath + uploadImageName);
		Files.copy(inputFile.getInputStream(), uploadPath);

	}
}
