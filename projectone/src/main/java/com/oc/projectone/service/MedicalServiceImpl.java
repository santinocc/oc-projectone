package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.Medical;
import com.oc.projectone.repository.MedicalRepository;

@Service
public class MedicalServiceImpl implements MedicalService {

	@Autowired
	MedicalRepository medicalRepository;

	
	public List<Integer> getPatientAges() {
		
		List<Integer> patientAges = new ArrayList<>();
		List<Medical> records = medicalRepository.getMedicalRecords();
		
		for (Medical record : records) {
			
			String birthdate = record.birthdate;
			LocalDate dob = LocalDate.parse(birthdate);
			
			int patientAge = calculateAge(dob);
			patientAges.add(patientAge);
			
		}
		
		System.out.println(patientAges);
		return patientAges;
		
	}
	
	
	public static int calculateAge(LocalDate dob) {
		
		LocalDate dateNow = LocalDate.now();
		
		if ((dob != null) && (dateNow != null))   {  
			return Period.between(dob, dateNow).getYears();  
		} else {  
		return 0;  
		}  
	}
}
