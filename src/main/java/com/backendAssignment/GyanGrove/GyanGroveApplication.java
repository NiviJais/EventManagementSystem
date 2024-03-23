package com.backendAssignment.GyanGrove;

import com.backendAssignment.GyanGrove.Service.CsvDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GyanGroveApplication {

	@Autowired
	private CsvDataService csvDataService;

	public static void main(String[] args) {
		SpringApplication.run(GyanGroveApplication.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
		// Provide the relative path to your CSV file
		String csvFilePath = "https://drive.google.com/file/d/1sZXyOT_V1NcZj3dDQIKY9Ea_W7XdGum_/view"; // Assuming data.csv is in the src/main/resources directory
		csvDataService.saveCsvData(csvFilePath);
	}

}
