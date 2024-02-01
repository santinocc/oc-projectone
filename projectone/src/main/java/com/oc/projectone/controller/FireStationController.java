package com.oc.projectone.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.model.responses.FireInfoResponse;
import com.oc.projectone.model.responses.PersonInfo;
import com.oc.projectone.service.FireStationServiceImpl;


@RestController
public class FireStationController {

	private final Logger logger = LoggerFactory.getLogger(FireStationController.class);
	
	@Autowired
	FireStationServiceImpl fireStationServiceImpl;
	
	
	@GetMapping("/fire") //Get StationID for now, later work on return StationID + special FireList with people living in that address
	public FireInfoResponse getFireInfoResponse(@RequestParam String address) {
		logger.info("HTTP GET request received at /fire URL");
		return fireStationServiceImpl.getFireInfoResponse(address);
	}
}
