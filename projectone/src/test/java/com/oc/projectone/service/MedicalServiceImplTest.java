package com.oc.projectone.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.oc.projectone.model.Medical;
import com.oc.projectone.repository.MedicalRepository;

@RunWith(MockitoJUnitRunner.class)
class MedicalServiceImplTest {
	
	@Mock
	private MedicalRepository medicalRepositoryMock;
	
	@Autowired
	PersonServiceImpl personServiceImpl;    //This and maybe the Persons and Medical Repositories would be required for the data to be validated
	
	@InjectMocks
	MedicalServiceImpl medicalServiceImpl;
	
	
	Medical medical1 = new Medical("Luffy", "MonkeyD", "03/06/1991", new String[] {"aznal:350mg", "hydrapermazal:100mg"}, new String[] {"nilluyin"});	
	Medical medical2 = new Medical("John", "Boyd", "03/06/1991", new String[] {"aznal:350mg", "hydrapermazal:100mg"}, new String[] {"nilluyin"});
	
	//HTTP METHODS TEST
	
	@Test //MEDICAL POST
	void testAddMedical() {
		//Arrange
		List<Medical> medicalList = medicalRepositoryMock.getMedicalRecords();
		Integer medicalData = medicalRepositoryMock.getMedicalRecords().size();
		
		//Act
		medicalServiceImpl.addMedical(medical1);
		Integer medicalDataAfter = medicalRepositoryMock.getMedicalRecords().size();
		
		//Assert
		assertTrue(medicalDataAfter == (medicalData + 1));
		assertTrue(medicalList.get(-1).firstName.equals("Luffy"));
	}
	
	@Test //MEDICAL DELETE
	void testDeleteMedical() {
		//Arrange
		List<Medical> medicalList = medicalRepositoryMock.getMedicalRecords();
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
		List<Medical> medicalList = medicalRepositoryMock.getMedicalRecords();
		
		//Act
		Boolean updateMedical = medicalServiceImpl.updateMedical(medical2);
		
		//Assert
		assertTrue(medicalList.get(0).birthdate.equals("03/06/1991"));
	}

}
