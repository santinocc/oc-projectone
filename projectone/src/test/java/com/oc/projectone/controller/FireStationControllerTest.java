package com.oc.projectone.controller;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oc.projectone.model.firestations.ServicedPerson;
import com.oc.projectone.model.persons.Households;
import com.oc.projectone.model.responses.FireInfoResponse;
import com.oc.projectone.repository.FireStationRepository;
import com.oc.projectone.service.FireStationServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class FireStationControllerTest {

	@Autowired
	FireStationRepository fireStationRepository;    //This and maybe the Persons and Medical Repositories would be required for the data to be validated
	
	@Autowired
	FireStationServiceImpl fireStationServiceImpl;
	
	@Test //getFireInfoResponse URL 4/7
	void testGetFireInfoResponse() {
		//Arrange
		String address = "29 15th St";
		
		//Act
		FireInfoResponse fireInfoResponse = fireStationServiceImpl.getFireInfoResponse(address);
		
		//Assert
		assertTrue(fireInfoResponse.station.equals("2"));
	}
	
	@Test //getPhoneAlert URL 3/7
	void testGetPhoneAlert() {
		//Arrange
		String station = "3";
		
		//Act
		List<ServicedPerson> servicedPersons = fireStationServiceImpl.getServicedPeople(station).servicedPersons;
		
		//Assert
		assertTrue(servicedPersons.size() == fireStationServiceImpl.getPhoneAlert(station).size());
	}
	
	@Test //getServicedPeople URL 1/7
	void testGetServicedPeople() {
		//Arrange
		String station = "2";
		
		//Act
		List<ServicedPerson> servicedPersons = fireStationServiceImpl.getServicedPeople(station).servicedPersons;
		
		//Assert
		assertTrue(servicedPersons.size() == 5);
	}
	
	@Test //getHouseholdsPerJurisdiction URL 5/7
	void testGetHouseholdsPerJurisdiction() {
		//Arrange
		String station = "4";
		
		//Act
		List<Households> households = fireStationServiceImpl.getHouseholdsPerJurisdiction(station);
		
		//Assert
		assertTrue(households.get(0).getClass().equals(Households.class));
	}
	

}
