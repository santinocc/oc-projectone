package com.oc.projectone.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.model.responses.FireInfoResponse;
import com.oc.projectone.model.responses.ServicedPeople;
import com.oc.projectone.service.FireStationServiceImpl;


@RestController
public class FireStationController {

	private final Logger logger = LoggerFactory.getLogger(FireStationController.class);
	
	@Autowired
	FireStationServiceImpl fireStationServiceImpl;
	
	
	@GetMapping("/fire") 
	public FireInfoResponse getFireInfoResponse(@RequestParam String address) {
		logger.info("HTTP GET request received at /fire URL");
		return fireStationServiceImpl.getFireInfoResponse(address);
	}
	
	@GetMapping("/phoneAlert") 
	public List<String> getPhoneAlert(@RequestParam String station) {
		logger.info("HTTP GET request received at /phoneAlert URL");
		return fireStationServiceImpl.getPhoneAlert(station);
	}
	
	@GetMapping("/firestation") 
	public ServicedPeople getServicedPeople(@RequestParam String station) {
		logger.info("HTTP GET request received at /firestation URL");
		return fireStationServiceImpl.getServicedPeople(station);
	}
}
