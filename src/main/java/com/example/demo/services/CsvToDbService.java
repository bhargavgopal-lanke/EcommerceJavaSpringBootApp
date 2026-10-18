package com.example.demo.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.DefGenericEquipments;
import com.example.demo.Repository.DefGenericEquipmentsReposiory;
import com.opencsv.CSVReader;

@Service
public class CsvToDbService {

	// validate file type -> if not csv throw error
	// add opencsv dependency
	// use third party library ad get whole data as array
	// upload to db table

	@Autowired
	private DefGenericEquipmentsReposiory defGenericEquipmentsReposiory;

	public void handleUploadCsvToDb(MultipartFile inputCsvFile) throws Exception {
		String fileName = StringUtils.cleanPath(inputCsvFile.getOriginalFilename());
		String fileExtension = StringUtils.getFilenameExtension(fileName);

		if (fileExtension.equals("csv") == false) {
			throw new Exception("Not a valid file type. Please upload csv file.");
		}
		try (Reader reader = new BufferedReader(new InputStreamReader(inputCsvFile.getInputStream()));
				CSVReader csvReader = new CSVReader(reader);) {
			// this will read all the text files without burden on the system

			String[] csvRow = csvReader.readNext();
			csvRow = csvReader.readNext();

			List<DefGenericEquipments> defObjectsList = new ArrayList<>();

			while (csvRow != null) {
				DefGenericEquipments defObject = new DefGenericEquipments();
				defObject.setGenericEquipmentId(Integer.parseInt(csvRow[0]));
				defObject.setEquipmentCategoryId(Integer.parseInt(csvRow[1]));
				defObject.setGenericEquipmentName(csvRow[2]);
				defObject.setGenericEquipmentValue(csvRow[3]);
				defObjectsList.add(defObject);

				// adds 1000 values at a time inside the db
				if (defObjectsList.size() == 1000) {
					defGenericEquipmentsReposiory.saveAll(defObjectsList);
					defObjectsList.clear();
				}

				if (defObjectsList.size() > 0) {
					defGenericEquipmentsReposiory.saveAll(defObjectsList);
					defObjectsList.clear();
				}

				csvRow = csvReader.readNext();
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}

	}
}
