package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.firestations.ServicedPerson;
import com.oc.projectone.model.persons.Adult;
import com.oc.projectone.model.persons.Child;
import com.oc.projectone.model.FireStation;
import com.oc.projectone.model.Medical;
import com.oc.projectone.model.Person;
import com.oc.projectone.model.firestations.FireInfo;
import com.oc.projectone.model.responses.FireInfoResponse;
import com.oc.projectone.model.responses.PersonInfo;
import com.oc.projectone.model.responses.ServicedPeople;
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
	
	
	public ServicedPeople getServicedPeople(String station) {
		
		ServicedPeople servicedPeople = null;
		List<ServicedPerson> servicedPersons = getServicedPersons(station);    //TODO: Make this method work and get adults and children with arraysize of methods from PersonServiceImpl
//		Integer adults = 
//		Integer children =
//		
//		USE THE METHODS FROM PersonServiceImpl by having the address	
//		List<Child> getChildrenList(String address);
//		
//		List<Adult> getAdultsList(String address);
		
		
		return servicedPeople;
	}
	
	public List<ServicedPerson> getServicedPersons(String station) {
		
		List<ServicedPerson> servicedPersons = new ArrayList<>();
		List<FireStation> fireStations = fireStationRepository.getFireStations();
		List<Person> persons = personRepository.getPersons();
		
		for (FireStation fireStation : fireStations) {
			
//			System.out.println(fireStation.getStationNumber().contains(station));  //TODO: Get the address that corresponds to the STATION MENTIONED, from each address, return the ServicedPerson Info of each resident.
			
			if(fireStation.getStationNumber().contains(station)) {
				
				for (Person person : persons) {
				
					if(fireStation.getAddresses().contains(person.address)) {
					
						ServicedPerson servicedPerson = new ServicedPerson(person.firstName, person.lastName, person.address, person.phone);
						servicedPersons.add(servicedPerson);
					}
				}
			
			}
		}
		return servicedPersons;
	}
	
	public Integer getServicedAdults(String station) {
		
		List<FireStation> fireStations = fireStationRepository.getFireStations();
		List<Person> persons = personRepository.getPersons();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		int servicedAdults = 0;
		
		for (FireStation fireStation : fireStations) {
			
			if(fireStation.getStationNumber().contains(station)) {
				
				for (Person person : persons) {
				
					if(fireStation.getAddresses().contains(person.address)) {
					
						Integer age = calculateAge(medicals.get(index).birthdate);
						if (age >= 18) {
							servicedAdults++;
						} 
					}
				}	
			}
		}
		return servicedAdults;
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
