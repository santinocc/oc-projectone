package com.oc.projectone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projectone.dataminer.model.Person;
import com.oc.projectone.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	PersonRepository personRepository;
	PersonService personService;
	
	@Autowired
	public PersonServiceImpl(PersonRepository personRepository, PersonService personService) {
		super();
		this.personRepository = personRepository;
		this.personService = personService;
	}
	
	public List<String> getCommunityEmail(String city) {
		
		List<String> emails = new ArrayList<>();
		List<Person> persons = personRepository;
		
		for (Person person : persons) {
			if (person.city.equals(city)) {
				emails.add(person.email);
			}
		}
		
		return emails;
	}
}
