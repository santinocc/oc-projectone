package com.oc.projectone.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.Medical;
import com.oc.projectone.model.Person;
import com.oc.projectone.model.persons.Adult;
import com.oc.projectone.model.persons.Child;
import com.oc.projectone.model.responses.ChildAlert;
import com.oc.projectone.model.responses.PersonInfo;
import com.oc.projectone.repository.PersonRepository;
import com.oc.projectone.repository.MedicalRepository;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	MedicalRepository medicalRepository;
	
	
	public List<PersonInfo> getPersonInfo(String firstName, String lastName) {
		
		List<PersonInfo> chosenPersons = new ArrayList<>();
		List<Person> persons = personRepository.getPersons();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		
		
		for (Person person : persons) {
			Medical medical = getMedical(person.firstName, person.lastName, medicals);
			if ((person.firstName == firstName) && (person.lastName == lastName)) {
				PersonInfo personInfo = new PersonInfo(person.firstName, person.lastName, person.address, calculateAge(medical.birthdate), person.email, medical.medications, medical.allergies);
				chosenPersons.add(personInfo);
			} else {
				System.out.println("There is no any DATA with that FIRST & LAST NAMES Together");
			}
		}
		
		System.out.println(chosenPersons);
		return chosenPersons;
	}
	
	
	public List<String> getCommunityEmail(String city) {
		
		List<String> emails = new ArrayList<>();
		List<Person> persons = personRepository.getPersons();
		
		for (Person person : persons) {
			if (person.city.equals(city)) {
				emails.add(person.email);
			}
		}
		
		System.out.println(emails);
		return emails;
	}
	
	
	public Integer calculateAge(String birthdate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate dob = LocalDate.parse(birthdate, formatter);
		LocalDate dateNow = LocalDate.now();
		
		return Period.between(dob, dateNow).getYears();
	}
	
	
	public Medical getMedical(String firstName, String lastName, List<Medical> medicals) { //TODO: This method is not working as expected
		for (Medical medical : medicals) {
			if ((medical.firstName == firstName) && (medical.lastName == lastName)) {
				return medical;
			}
		}
		return null;
	}
	
	public List<Child> getChildrenList(String address) {
		
		List<Child> children = new ArrayList<>();
		List<Person> persons = personRepository.getPersons();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		int index = -1;
		
		for (Person person : persons) {
			
			index++;
			if (person.address.equals(address)) {

				Integer age = calculateAge(medicals.get(index).birthdate);
				if (age < 18) {
					Child child = new Child(medicals.get(index).firstName, medicals.get(index).lastName, calculateAge(medicals.get(index).birthdate));
					
					children.add(child);
				} 
			}
		}
		return children;
	}
	
	public List<Adult> getAdultsList(String address) {
		
		List<Adult> adults = new ArrayList<>();
		List<Person> persons = personRepository.getPersons();
		List<Medical> medicals = medicalRepository.getMedicalRecords();
		int index = -1;
		
		for (Person person : persons) {
			
			index++;
			if (person.address.equals(address)) {

				Integer age = calculateAge(medicals.get(index).birthdate);
				if (age >= 18) {
					Adult adult = new Adult(medicals.get(index).firstName, medicals.get(index).lastName);
					
					adults.add(adult);
				} 
			}
		}
		return adults;
	}
	
	public ChildAlert getChildAlert(String address) {
		
		List<Child> children = getChildrenList(address);
		List<Adult> adults = new ArrayList<Adult>();

		if (children.size() != 0) {
			adults = getAdultsList(address);
		} else {
			adults = new ArrayList<Adult>();
		}
		
		ChildAlert childAlert = new ChildAlert(children, adults);
		
		return childAlert;
	}
}
