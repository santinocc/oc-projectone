package com.oc.projectone.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oc.projectone.model.responses.ChildAlert;
import com.oc.projectone.model.responses.PersonInfo;
import com.oc.projectone.service.FireStationServiceImpl;
import com.oc.projectone.service.MedicalServiceImpl;
import com.oc.projectone.service.PersonServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PersonControllerTest {

	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;    //This and maybe the Persons and Medical Repositories would be required for the data to be validated
	
	@Autowired
	FireStationServiceImpl fireStationServiceImpl;
	
	
	
	@Test //getFireInfoResponse URL 6/7
	void testGetPersonInfo() {
		//Arrange
		String firstName = "John";
		String lastName = "Boyd";
		
		//Act
		List<PersonInfo> personInfoArray = personServiceImpl.getPersonInfo(firstName, lastName);
		
		PersonInfo personInfo = personInfoArray.get(0);
		
		//Assert
		assertTrue(personInfo.firstName.equals("John"));
		assertFalse(personInfo.address.isEmpty());
	}
	
	
	@Test //getServicedPeople URL 7/7
	void testGetCommunityEmail() {
		//Arrange
		String city = "Culver";
		
		//Act
		List<String> emails = personServiceImpl.getCommunityEmail(city);
		
		//Assert
		assertTrue(emails.size() == 24);
	}
	
	@Test //getChildAlert URL 2/7
	void testGetChildAlert() {
		//Arrange
		String address = "1509 Culver St";
		
		//Act
		ChildAlert childAlert = personServiceImpl.getChildAlert(address);
		
		//Assert
		assertFalse(childAlert.children.equals(null));
	}

}
