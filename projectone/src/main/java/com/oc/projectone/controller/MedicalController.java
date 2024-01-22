package com.oc.projectone.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.service.MedicalServiceImpl;

@RestController
public class MedicalController {
	
	private final Logger logger = LoggerFactory.getLogger(MedicalController.class);
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;
	
	@GetMapping("/childAlert")
	public List<Integer> getPatientAges() {
		logger.info("HTTP GET request received at /childAlert URL");
		return medicalServiceImpl.getPatientAges();
	}
	

	
	
}


