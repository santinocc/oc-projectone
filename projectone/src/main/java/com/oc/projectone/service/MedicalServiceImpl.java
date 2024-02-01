package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.Medical;
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
	
}
