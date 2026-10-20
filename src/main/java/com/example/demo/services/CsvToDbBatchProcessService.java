package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.DefGenericEquipments;
import com.example.demo.Repository.DefGenericEquipmentsReposiory;

@Service
public class CsvToDbBatchProcessService {

	@Autowired
	private DefGenericEquipmentsReposiory defGenericEquipmentsReposiory;

	// 100,000
	// loop 1000 times
	// 1 -> 1000 db lo insert avthai
	// loop 1000 times
	// 2 -> 1000 db lo insert avthai
	// loop 1000 times
	// 3 -> 1000 db lo insert avthai

	// if we want to run a method on threads we have to use async

	@Async("csvAsyncConfig")
	public void processbatchUpload(List<DefGenericEquipments> defObjectsList) {
		try {
			System.out.println("Inside processbatchUpload: " + Thread.currentThread().getName());
			defGenericEquipmentsReposiory.saveAll(defObjectsList);
		} catch (Exception e) {
			System.out.println("Inside Exceptiion" + e.getMessage());
			System.out.println("Inside Exceptiion" + defObjectsList);
		}
	}
}
