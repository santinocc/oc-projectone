package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.firestations.ServicedPerson;
import com.oc.projectone.model.FireStation;
import com.oc.projectone.model.persons.Household;
import com.oc.projectone.model.persons.Households;
import com.oc.projectone.model.Medical;
import com.oc.projectone.model.Person;
import com.oc.projectone.model.firestations.FireInfo;
import com.oc.projectone.model.responses.FireInfoResponse;
import com.oc.projectone.model.responses.ServicedPeople;
import com.oc.projectone.repository.FireStationRepository;
import com.oc.projectone.repository.MedicalRepository;
import com.oc.projectone.repository.PersonRepository;

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
		List<ServicedPerson> servicedPersons = new ArrayList<>();;
		Integer adults = 0;
		Integer children = 0;
		List<FireStation> fireStations = fireStationRepository.getFireStations();
		List<Person> persons = personRepository.getPersons();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		int index = -1;

		for (FireStation fireStation : fireStations) {
						
			if(fireStation.getStationNumber().contains(station)) {
				
				for (Person person : persons) {
				
					index++;
					
					if(fireStation.getAddresses().contains(person.address)) {
					
						ServicedPerson servicedPerson = new ServicedPerson(person.firstName, person.lastName, person.address, person.phone);
						servicedPersons.add(servicedPerson);
						
						Integer age = personServiceImpl.calculateAge(medicals.get(index).birthdate);
						if (age >= 18) {
							adults++;
						} else {
							children++;
						}
					}
				}
			
			}
		}
		
		servicedPeople = new ServicedPeople(station, servicedPersons, adults, children);
		
		return servicedPeople;
	}
	
	
	public List<String> getPhoneAlert(String station) {
		
		List<String> phones = new ArrayList<>();
		List<ServicedPerson> servicedPersons = getServicedPeople(station).servicedPersons;
		
		
		for (ServicedPerson servicedPerson : servicedPersons) {
			
			phones.add(servicedPerson.phoneNumber);
		}
		return phones;
	}
	
	public List<Households> getHouseholdsPerJurisdiction(String station) {
		
		List<Household> households = new ArrayList<>();
		
		Households sameFamily = null;
		List<Households> householdsPerJurisdiction = new ArrayList<>();
		List<FireStation> fireStations = fireStationRepository.getFireStations();
		List<Person> persons = personRepository.getPersons();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		

		for (FireStation fireStation : fireStations) {
						
			if(fireStation.getStationNumber().contains(station)) {
								
				for (String address : fireStation.getAddresses()) {
					int index = -1;
					
					for (Person person : persons) {
					
						index++;
							
							if(address.contains(person.address)) {
								
								Household household = new Household(person.firstName, person.lastName, person.phone, personServiceImpl.calculateAge(medicals.get(index).birthdate), medicals.get(index).medications, medicals.get(index).allergies);     
								
								households.add(household);
							
							}
						sameFamily = new Households(households);     // THIS IS THE GROUP OF PEOPLE LIVING IN SAME HOUSEHOLD ADDRESS
					}
				}
				householdsPerJurisdiction.add(sameFamily);		
			}	
		}
		
		return householdsPerJurisdiction;
		
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


	//POST//PUT//DELETE
	public FireStation addFireStation(String address, String station) {
		
		List<FireStation> firestations = fireStationRepository.getFireStations();

		boolean isExisted = false;
		
		
		for (FireStation firestation : firestations) {     

			if (firestation.getStationNumber().equals(station)) { 
	
				firestation.addAddress(address);
				isExisted = true;
			} 
		}
		
		if (!isExisted) { 
			FireStation firestation = new FireStation(station);
			firestation.addAddress(address);
			firestations.add(firestation);
		}
		

		return null;
		
	}

	public FireStation deleteFireStation(String address) {
		  List<FireStation> firestations = fireStationRepository.getFireStations();
		  Iterator<FireStation> it = firestations.iterator(); // Use an iterator

		  while (it.hasNext()) {
		    FireStation firestation = it.next();
		    if (firestation.getAddresses().contains(address)) {
		      firestation.getAddresses().remove(address); // This should remove the address from the the station Set<String>
		    }
		  }
		return null;

	}


	public boolean updateFireStation(String address, String station) {
	
		List<FireStation> firestations = fireStationRepository.getFireStations();
		
		boolean isUpdated = false;
		
		
		for (FireStation firestation : firestations) {     

			if (firestation.getAddresses().contains(address)) { 
				
				firestation.getAddresses().remove(address);         // It is possible this part is not removing the address from OLD Station #
			} 
			
			if (firestation.getStationNumber().equals(station)) { 
	
				firestation.addAddress(address);
				isUpdated = true;
			} 
		}

        return isUpdated;

	}

}
