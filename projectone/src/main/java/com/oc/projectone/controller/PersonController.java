package com.oc.projectone.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.oc.projectone.service.PersonServiceImpl;


@Controller
public class PersonController {

	private final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail() {
		logger.info("HTTP GET request received at /communityEmail URL");
		return personServiceImpl.getCommunityEmail("Culver");
	}
}
