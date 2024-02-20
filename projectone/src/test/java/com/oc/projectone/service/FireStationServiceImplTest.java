package com.oc.projectone.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.oc.projectone.model.FireStation;
import com.oc.projectone.model.Person;
import com.oc.projectone.repository.FireStationRepository;
import com.oc.projectone.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
class FireStationServiceImplTest {
	
	@Mock
	private FireStationRepository fireStationRepositoryMock;
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;
	
	@InjectMocks
	FireStationServiceImpl fireStationServiceImpl;
	
	
	FireStation fireStation1 = new FireStation("5");
	FireStation fireStation2 = new FireStation("3");
	
	//HTTP METHODS TEST
	
	@Test //FIRESTATION POST
	void testAddFireStation() {
		//Arrange
		List<FireStation> fireStationList = fireStationRepositoryMock.getFireStations();
		Integer fireStationData = fireStationRepositoryMock.getFireStations().size();
		
		//Act
		fireStationServiceImpl.addFireStation("New St", "5");
		Integer fireStationDataAfter = fireStationRepositoryMock.getFireStations().size();
		
		//Assert
		assertTrue(fireStationDataAfter == (fireStationData + 1));
		assertTrue(fireStationList.get(-1).getAddresses().equals("New St"));
	}
	
	@Test //FIRESTATION DELETE
	void testDeleteFireStation() {
		//Arrange
		List<FireStation> fireStationList = fireStationRepositoryMock.getFireStations();
		Integer fireStationData = fireStationList.size();
		
		//Act
		fireStationServiceImpl.addFireStation("New St", "5");
		fireStationServiceImpl.deleteFireStation("New St");
		
		//Assert
		assertTrue(fireStationData == fireStationList.size());
	}
	
	@Test //FIRESTATION PUT
	void testUpdateFireStation() {
		//Arrange
		List<FireStation> fireStationList = fireStationRepositoryMock.getFireStations();
		
		//Act
		Boolean updateFireStation = fireStationServiceImpl.updateFireStation("Newer St", "5");
		
		//Assert
		assertTrue(fireStationList.get(-1).getAddresses().contains("Newer St"));
	}

}
