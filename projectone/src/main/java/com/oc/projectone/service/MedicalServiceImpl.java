package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
			
			patientAge = calculateAge(birthdate);
			patientAges.add(patientAge);
			
		}
		
		System.out.println(patientAges);
		return patientAges;
		
	}
	
	
//	public static int calculateAge(LocalDate dob) {
//		
//		LocalDate dateNow = LocalDate.now();
//		System.out.println("This is" + dateNow);
//		
//		if ((dob != null) && (dateNow != null))   {  
//			return Period.between(dob, dateNow).getYears();  
//		} else {  
//		return 0;  
//		}  
//	}
	
//TODO: FIGURE OUT HOW TO USE 'calculateAge' FROM 'PersonServiceImpl' RATHER THAN CREATING ANOTHER SAME METHOD TWICE
	
	public static int calculateAge(String birthdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dob = LocalDate.parse(birthdate, formatter);
		LocalDate dateNow = LocalDate.now();
		
		return Period.between(dob, dateNow).getYears();
	}
}
