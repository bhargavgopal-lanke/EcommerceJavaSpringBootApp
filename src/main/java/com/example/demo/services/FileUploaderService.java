package com.example.demo.services;

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
	public void handleFileUpload(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileTypeString = StringUtils.getFilenameExtension(file.getOriginalFilename());
	}
}
