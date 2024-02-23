package com.oc.projectone.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oc.projectone.model.Medical;
import com.oc.projectone.repository.MedicalRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MedicalServiceImplTest {
	
	@Autowired
	MedicalRepository medicalRepository;
	
	@Autowired
	PersonServiceImpl personServiceImpl;    //This and maybe the Persons and Medical Repositories would be required for the data to be validated
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;
	
	
	Medical medical1 = new Medical("Luffy", "MonkeyD", "03/06/1991", new String[] {"aznal:350mg", "hydrapermazal:100mg"}, new String[] {"nilluyin"});	
	Medical medical2 = new Medical("John", "Boyd", "03/06/1991", new String[] {"aznal:350mg", "hydrapermazal:100mg"}, new String[] {"nilluyin"});
	
	//HTTP METHODS TEST
	
	@Test //MEDICAL POST
	void testAddMedical() {
		//Arrange
		List<Medical> medicalList = medicalRepository.getMedicalRecords();
		Integer medicalData = medicalRepository.getMedicalRecords().size();
		
		//Act
		medicalServiceImpl.addMedical(medical1);
		Integer medicalDataAfter = medicalRepository.getMedicalRecords().size();
		
		//Assert
		assertTrue(medicalDataAfter == (medicalData + 1));
	}
	
	@Test //MEDICAL DELETE
	void testDeleteMedical() {
		//Arrange
		List<Medical> medicalList = medicalRepository.getMedicalRecords();
		Integer medicalData = medicalList.size();
		
		//Act
		medicalServiceImpl.addMedical(medical1);
		medicalServiceImpl.deleteMedical("Luffy", "MonkeyD");
		
		//Assert
		assertTrue(medicalData == medicalList.size());
	}
	
	@Test //MEDICAL PUT
	void testUpdateMedical() {
		//Arrange
		List<Medical> medicalList = medicalRepository.getMedicalRecords();
		
		//Act
		Boolean updateMedical = medicalServiceImpl.updateMedical(medical2);
		
		//Assert
		assertTrue(medicalList.get(0).birthdate.equals("03/06/1991"));
	}

}
