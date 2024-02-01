package com.oc.projectone.service;

import java.util.List;

import com.oc.projectone.model.firestations.FireInfo;
import com.oc.projectone.model.responses.FireInfoResponse;

public interface FireStationService {
	
	String getFireStationNumber(String address);
	
	List<FireInfo> getFireInfos(String address);
	
	FireInfoResponse getFireInfoResponse(String address);

}
