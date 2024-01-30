package com.oc.projectone.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.model.responses.ChildAlert;
import com.oc.projectone.model.responses.PersonInfo;
import com.oc.projectone.service.PersonServiceImpl;
import com.oc.projectone.service.MedicalServiceImpl;


@RestController
public class PersonController {

	private final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;
	
	@GetMapping("/personInfo")
	public List<PersonInfo> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("HTTP GET request received at /personInfo URL");
		return personServiceImpl.getPersonInfo(firstName, lastName);
	}
	
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam String city) {
		logger.info("HTTP GET request received at /communityEmail URL");
		return personServiceImpl.getCommunityEmail(city);
	}
	
	@GetMapping("/childAlert")
	public ChildAlert getChildAlert(@RequestParam(required = true) String address) {
		logger.info("HTTP GET request received at /childAlert URL");
		return personServiceImpl.getChildAlert(address);
	}  
}
