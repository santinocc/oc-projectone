package com.oc.projectone.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.Medical;
import com.oc.projectone.model.Person;
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
		  Iterator<Medical> it = medicals.iterator(); // Use an iterator

		  while (it.hasNext()) {
		    Medical medical = it.next();
		    if (medical.firstName.equals(firstName) && medical.lastName.equals(lastName)) {
		      it.remove(); // Safely remove using the iterator
		    }
		  }
		return null;
	}
	
	public boolean updateMedical(Medical medicalUpdate) {
		
		List<Medical> medicals = medicalRepository.getMedicalRecords();
        boolean isUpdated = false;

        for (Medical medical : medicals) {
            if (medical.firstName.equals(medicalUpdate.firstName) && medical.lastName.equals(medicalUpdate.lastName)) {
                medical.birthdate = medicalUpdate.birthdate;
                medical.medications = medicalUpdate.medications;
                medical.allergies = medicalUpdate.allergies;

                isUpdated = true;
                break;
            }
        }
        return isUpdated;
	}
	
}
