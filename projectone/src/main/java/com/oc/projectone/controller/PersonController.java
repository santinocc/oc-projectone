package com.oc.projectone.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projectone.model.Person;
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
	public List<PersonInfo> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {  //Do I need @RequestParam(required = true)?
		logger.info("HTTP GET request received at /personInfo URL");
		return personServiceImpl.getPersonInfo(firstName, lastName);
	}
	
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam String city) {                                      //Do I need @RequestParam(required = true)?
		logger.info("HTTP GET request received at /communityEmail URL");
		return personServiceImpl.getCommunityEmail(city);
	}
	
	@GetMapping("/childAlert")
	public ChildAlert getChildAlert(@RequestParam(required = true) String address) {
		logger.info("HTTP GET request received at /childAlert URL");
		return personServiceImpl.getChildAlert(address);
	}  
	
	// POST/PUT/DELETE
	@PostMapping("/person")
	public Person addPerson(@RequestBody Person person) {
		logger.info("HTTP POST request received at /person URL to create 1 Person");
		return personServiceImpl.addPerson(person);
	}
	
	@DeleteMapping("/person")
	public Person deletePerson(@RequestParam String firstName, @RequestParam String lastName) {               // I think the Person class is not required
		logger.info("HTTP DELETE request received at /person URL to delete 1 Person called: " + firstName + " " + lastName);
		return personServiceImpl.deletePerson(firstName, lastName);
	}
}
