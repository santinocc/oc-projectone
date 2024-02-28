package com.oc.projectone.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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
		int index = -1;
		
		for (Person person : persons) {
			index++;     //This index approach ONLY works when BOTH persons and medicals Objects are consistent and in order for info of each Individual
			
//			Medical medical = getMedical(person.firstName, person.lastName, medicals);  //SOLUTION2

			if ((person.firstName.equals(firstName)) && (person.lastName.equals(lastName))) { 
	
				PersonInfo personInfo = new PersonInfo(person.firstName, person.lastName, person.address, calculateAge(medicals.get(index).birthdate), person.email, medicals.get(index).medications, medicals.get(index).allergies);
				chosenPersons.add(personInfo);
				
			} else {
				
				continue;
				
			}
		}

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


	public Person addPerson(Person person) {
		List<Person> persons = personRepository.getPersons();
		persons.add(person);
		return null;
	}


	public Person deletePerson(String firstName, String lastName) {
		
		List<Person> persons = personRepository.getPersons();
		
//		for (Person person : persons) {     
//
//			if ((person.firstName.equals(firstName)) && (person.lastName.equals(lastName))) { 
//	
//				persons.remove(person);
//				
//			}
//		}
		Iterator<Person> it = persons.iterator(); // Use an iterator

		  while (it.hasNext()) {
		    Person person = it.next();
		    if (person.firstName.equals(firstName) && person.lastName.equals(lastName)) {
		      it.remove(); // Safely remove using the iterator
		    }
		  }

		return null;
	}


	public boolean updatePerson(Person personUpdate) {
		
		List<Person> persons = personRepository.getPersons();
        boolean isUpdated = false;

        for (Person person : persons) {
            if (person.firstName.equals(personUpdate.firstName) && person.lastName.equals(personUpdate.lastName)) {
                person.address = personUpdate.address;
                person.city = personUpdate.city;
                person.zip = personUpdate.zip;
                person.phone = personUpdate.phone;
                person.email = personUpdate.email;

                isUpdated = true;
                break;
            }
        }
        return isUpdated;
	}
}
