package com.oc.projectone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.FireStation;
import com.oc.projectone.repository.FireStationRepository;

@Service
public class FireStationServiceImpl implements FireStationService {

	@Autowired
	FireStationRepository fireStationRepository;

	
	public String getFireStationNumber(String address) {
		
		String fireStationChosen = null;
		List<FireStation> fireStations = fireStationRepository.getFireStations();
		
		for (FireStation fireStation : fireStations) {
			
			if(fireStation.getAddresses().contains("1509 Culver St")) {
				System.out.println("Firestation " + fireStation.getStationNumber() + " selected");
				fireStationChosen = fireStation.getStationNumber();
				break;
				
			}
		}
		return fireStationChosen;
	}
	
}
