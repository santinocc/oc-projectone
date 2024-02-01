package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.FireStation;
import com.oc.projectone.model.Medical;
import com.oc.projectone.model.Person;
import com.oc.projectone.model.firestations.FireInfo;
import com.oc.projectone.model.responses.FireInfoResponse;
import com.oc.projectone.model.responses.PersonInfo;
import com.oc.projectone.repository.FireStationRepository;
import com.oc.projectone.repository.MedicalRepository;
import com.oc.projectone.repository.PersonRepository;
import com.oc.projectone.service.PersonServiceImpl;

@Service
public class FireStationServiceImpl implements FireStationService {

	@Autowired
	FireStationRepository fireStationRepository;

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	MedicalRepository medicalRepository;
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	public String getFireStationNumber(String address) {
		
		String fireStationChosen = null;
		List<FireStation> fireStations = fireStationRepository.getFireStations();
		
		for (FireStation fireStation : fireStations) {
			
			if(fireStation.getAddresses().contains(address)) {
				System.out.println("Firestation " + fireStation.getStationNumber() + " selected");
				fireStationChosen = fireStation.getStationNumber();
				break;
				
			}
		}
		return fireStationChosen;
	}
	
	
	public List<FireInfo> getFireInfos(String address) {
		
		List<FireInfo> residents = new ArrayList<>();
		List<Person> persons = personRepository.getPersons();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		int index = -1;
		
		for (Person person : persons) {
			index++;     //This index approach ONLY works when BOTH persons and medicals Objects are consistent and in order for info of each Individual

			if (person.address.equals(address)) { 
	
				FireInfo fireInfo = new FireInfo(person.firstName, person.lastName, person.phone, personServiceImpl.calculateAge(medicals.get(index).birthdate), medicals.get(index).medications, medicals.get(index).allergies);
				residents.add(fireInfo);
				
			} else {
				
				continue;
				
			}
		}

		return residents;
	}


	public FireInfoResponse getFireInfoResponse(String address) {
		
		String station = getFireStationNumber(address);
		List<FireInfo> fireInfos = getFireInfos(address);
		
		FireInfoResponse fireInfoResponse = new FireInfoResponse(station, fireInfos);
		
		return fireInfoResponse;
	}
	
}
