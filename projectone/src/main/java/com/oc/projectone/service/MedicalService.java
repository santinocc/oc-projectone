package com.oc.projectone.service;

import java.util.List;

import com.oc.projectone.model.Medical;

public interface MedicalService {
	
	List<Integer> getPatientAges();
	
	public Medical addMedical(Medical medical);

}
