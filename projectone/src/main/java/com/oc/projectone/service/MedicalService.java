package com.oc.projectone.service;

import java.util.List;

import com.oc.projectone.model.Medical;

public interface MedicalService {
	
	List<Integer> getPatientAges();
	
	public Medical addMedical(Medical medical);
	
	public Medical deleteMedical(String firstName, String lastName);
	
	public boolean updateMedical(Medical medicalUpdate);

}
