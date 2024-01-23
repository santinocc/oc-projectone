package com.oc.projectone.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.model.PersonInfo;
import com.oc.projectone.service.FireStationServiceImpl;


@RestController
public class FireStationController {

	private final Logger logger = LoggerFactory.getLogger(FireStationController.class);
	
	@Autowired
	FireStationServiceImpl fireStationServiceImpl;
	
//	@GetMapping("/personInfo")
//	public List<PersonInfo> getPersonInfo() {
//		logger.info("HTTP GET request received at /personInfo URL");
//		return personServiceImpl.getPersonInfo("John", "Boyd");
//	}
	
	@GetMapping("/fire") //Get StationID for now, later work on return StationID + special FireList with people living in that address
	public String getFireStationNumber(String address) {
		logger.info("HTTP GET request received at /fire URL");
		return fireStationServiceImpl.getFireStationNumber("1509 Culver St");
	}
}
