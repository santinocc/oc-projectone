package com.oc.projectone.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oc.projectone.service.PersonServiceImpl;


@Controller
public class PersonController {

	private final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@GetMapping("/communityEmail")
	public ModelAndView getCommunityEmail() {
		logger.info("HTTP GET request received at /communityEmail URL");
		
		String viewName = "communityEmail";
		
		Map<String, Object> model = new HashMap<String,Object>();
		
		model.put(viewName, personServiceImpl.getCommunityEmail("Culver"));
		
		return new ModelAndView(viewName, model);
	}
}
