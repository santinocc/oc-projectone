package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.model.Person;
import com.oc.projectone.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;
//	PersonService personService;
	
//	@Autowired
//	public PersonServiceImpl(PersonRepository personRepository, PersonService personService) {
//		super();
//		this.personRepository = personRepository;
//		this.personService = personService;
//	}
	
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
