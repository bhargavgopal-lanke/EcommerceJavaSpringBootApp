package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.services.CsvToDbService;
import com.example.demo.services.FileUploaderService;
import com.example.demo.services.JwtService;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploaderService fileUploaderService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CsvToDbService csvToDbService;

	// Create Entity (it will create table in the database)
	// create Repository
	// Create Service
	// Create Endpoint

	@PostMapping("upload/csvToDb")
	public ResponseEntity<?> uploadCsvToDb(@RequestParam("csv_file") MultipartFile inputCsvFile) throws Exception {
		Map<String, Object> csvDataobj = new HashMap<String, Object>();
		csvDataobj.put("result", "success");
		csvDataobj.put("message", "csv file imported to db successfully");
		return ResponseEntity.status(HttpStatus.OK).body(csvDataobj);
	}

	// After login
	// Receive authorization header
	// Check if its null
	// Check if it starts with a Bearer.
	// Get token without bearer
	// Check if its expired or not
	// If all are OK, proceed to operation else throw error

	@PostMapping("upload/images")
	public ResponseEntity<Map<String, Object>> uploadImages(@RequestHeader("Authorization") String jwtToken,
			@RequestParam("whatsappimage") MultipartFile inputFile) throws Exception {

		if (jwtToken == null || jwtToken.startsWith("Bearer") == false) {
			throw new Exception("Unauthorized. You are not allowed to do this operation");
		}

		jwtToken = jwtToken.substring(7);

		Boolean isValid = jwtService.verifyJwtToken(jwtToken);

		Claims claims = jwtService.getJwtClaims(jwtToken);

		System.out.println(claims.get("exp"));

		fileUploaderService.handleFileUpload(inputFile);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "success");
		responseMap.put("message", "Image uploaded Successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

	@PostMapping("upload/pdf")
	public ResponseEntity<Map<String, Object>> uploadPdf(@RequestParam("pdfFile") MultipartFile inputFile)
			throws Exception {
		fileUploaderService.handlePdfUpload(inputFile);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("result", "Success");
		responseMap.put("message", "Pdf uploaded successfully");

		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

}
