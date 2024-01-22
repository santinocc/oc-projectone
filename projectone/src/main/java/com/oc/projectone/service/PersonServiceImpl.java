package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.Person;
import com.oc.projectone.model.PersonInfo;
import com.oc.projectone.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;
	
	
	public List<PersonInfo> getPersonInfo(String firstName, String lastName) {
		
		List<PersonInfo> chosenPersons = new ArrayList<>();
		List<Person> persons = personRepository.getPersons();
		
		for (Person person : persons) {
			if ((person.firstName == firstName) && (person.lastName == lastName)) {
				PersonInfo personInfo = new PersonInfo(person.firstName, person.lastName, person.address, age, person.email, medical.medications, medical.allergies);
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
}
