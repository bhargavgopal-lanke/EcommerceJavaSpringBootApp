package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploader {

	@PostMapping("upload/images")
	public ResponseEntity<?> uploadImages(@RequestParam("files") MultipartFile inputFile) {
		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("success", "true");
		responseMap.put("message", inputFile);
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}
}
