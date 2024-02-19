package com.oc.projectone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.model.Medical;
import com.oc.projectone.service.MedicalServiceImpl;

@RestController
public class MedicalController {
	
	private final Logger logger = LoggerFactory.getLogger(MedicalController.class);
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;
	

	// POST/PUT/DELETE
	@PostMapping("/medicalRecord")
	public Medical addMedical(@RequestBody Medical medical) {
		logger.info("HTTP POST request received at /medical URL to create 1 Medical Record");
		return medicalServiceImpl.addMedical(medical);
	}	
	
	@PutMapping("/medicalRecord")
	public boolean updateMedical(@RequestBody Medical medical) {
		logger.info("HTTP PUT request received at /medicalRecord URL to update 1 Person called: " + medical.firstName + ", " + medical.lastName);
		return medicalServiceImpl.updateMedical(medical);
	}
	
	@DeleteMapping("/medicalRecord")
	public Medical deleteMedical(@RequestParam String firstName, @RequestParam String lastName) {               // I think the Person class is not required
		logger.info("HTTP DELETE request received at /medicalRecord URL to delete 1 Medical Record from: " + firstName + " " + lastName);
		return medicalServiceImpl.deleteMedical(firstName, lastName);
	}
	
}


