package com.oc.projectone.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
}


