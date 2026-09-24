package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.FileUploaderService;

@RestController
public class FileUploadController {

	@Autowired
	FileUploaderService fileUploaderService;

	@PostMapping("upload/images")
	public ResponseEntity<Map<String, Object>> uploadImages(@RequestParam("whatsappimage") MultipartFile inputFile)
			throws Exception {
		fileUploaderService.handleFileUpload(inputFile);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "success");
		responseMap.put("message", inputFile);
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}
}
