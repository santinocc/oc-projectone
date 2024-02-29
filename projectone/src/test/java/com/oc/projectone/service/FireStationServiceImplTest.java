package com.oc.projectone.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oc.projectone.model.FireStation;
import com.oc.projectone.repository.FireStationRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class FireStationServiceImplTest {
	
	@Autowired
	FireStationRepository fireStationRepository;
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;
	
	@Autowired
	FireStationServiceImpl fireStationServiceImpl;
	
	
	FireStation fireStation1 = new FireStation("5");
	FireStation fireStation2 = new FireStation("3");
	
	//HTTP METHODS TEST
	
	@Test //FIRESTATION POST
	void testAddFireStation() {
		//Arrange
		List<FireStation> fireStationList = fireStationRepository.getFireStations();
		Integer fireStationData = fireStationRepository.getFireStations().get(0).getAddresses().size();
		
		//Act
		fireStationServiceImpl.addFireStation("New St", "1");
		Integer fireStationDataAfter = fireStationRepository.getFireStations().get(0).getAddresses().size();
		
		//Assert
		assertTrue(fireStationDataAfter == (fireStationData + 1));
	}
	
	@Test //FIRESTATION DELETE
	void testDeleteFireStation() {
		//Arrange
		List<FireStation> fireStationList = fireStationRepository.getFireStations();
		Integer fireStation3Data = fireStationList.get(2).getAddresses().size();
		
		//Act
		System.out.println(fireStationList);
		fireStationServiceImpl.deleteFireStation("112 Steppes Pl");
		System.out.println(fireStationList);
		fireStationServiceImpl.addFireStation("chuntaro", "5");
		System.out.println(fireStationList);
		
		Integer fireStation5Data = fireStationList.get(4).getAddresses().size();
		fireStationServiceImpl.deleteFireStation("chuntaro");
		System.out.println(fireStationList);
		
		//Assert
		assertTrue(fireStation3Data > fireStationList.get(2).getAddresses().size());
		assertTrue(fireStation5Data > fireStationList.get(4).getAddresses().size());
	}
	
	@Test //FIRESTATION PUT
	void testUpdateFireStation() {
		//Arrange
		List<FireStation> fireStationList = fireStationRepository.getFireStations();
		
		//Act
		Boolean updateFireStation = fireStationServiceImpl.updateFireStation("1509 Culver St", "1");
		
		//Assert
		assertTrue(fireStationList.get(0).getAddresses().contains("1509 Culver St"));
	}

}
