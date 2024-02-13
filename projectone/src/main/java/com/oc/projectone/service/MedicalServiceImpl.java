package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.Medical;
import com.oc.projectone.model.Person;
import com.oc.projectone.model.responses.PersonInfo;
import com.oc.projectone.repository.MedicalRepository;

@Service
public class MedicalServiceImpl implements MedicalService {

	@Autowired
	MedicalRepository medicalRepository;
	
	@Autowired
	PersonServiceImpl personServiceImpl;

	
	public List<Integer> getPatientAges() {
		
		List<Integer> patientAges = new ArrayList<>();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		
		System.out.println(medicals);
		String birthdate = null;
		LocalDate dob = null;
		int patientAge = 0;
		
		for (Medical medical : medicals) {
			
			birthdate = medical.birthdate;
			
			patientAge = personServiceImpl.calculateAge(birthdate);
			patientAges.add(patientAge);
			
		}
		
		System.out.println(patientAges);
		return patientAges;
		
	}


	public Medical addMedical(Medical medical) {
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		medicals.add(medical);
		return null;
	}


	public Medical deleteMedical(String firstName, String lastName) {
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		
		for (Medical medical : medicals) {

			if ((medical.firstName.equals(firstName)) && (medical.lastName.equals(lastName))) { 
	
				medicals.remove(medical);
				
			}
		}

		return null;
	}
	
}
