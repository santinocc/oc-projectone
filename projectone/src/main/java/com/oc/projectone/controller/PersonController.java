package com.oc.projectone.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.model.PersonInfo;
import com.oc.projectone.service.PersonServiceImpl;


@RestController
public class PersonController {

	private final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@GetMapping("/personInfo")
	public List<PersonInfo> getPersonInfo() {
		logger.info("HTTP GET request received at /personInfo URL");
		return personServiceImpl.getPersonInfo("John", "Boyd");
	}
	
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail() {
		logger.info("HTTP GET request received at /communityEmail URL");
		return personServiceImpl.getCommunityEmail("Culver");
	}
}
