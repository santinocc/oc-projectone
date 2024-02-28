package com.oc.projectone.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oc.projectone.model.Person;
import com.oc.projectone.repository.PersonRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PersonServiceImplTest {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;    //This and maybe the Persons and Medical Repositories would be required for the data to be validated
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	
	Person person1 = new Person("Luffy", "MonkeyD", "111-222-3333", "97451", "834 Binoc Ave", "Culver", "luffy@email.com");
	Person person2 = new Person("John", "Boyd", "999-888-7777", "97450", "1510 Culver St", "Culver", "jaboyd2@email.com");
	
	//HTTP METHODS TEST
	
	@Test //PERSON POST
	void testAddPerson() {
		//Arrange
		List<Person> personList = personRepository.getPersons();
		Integer personData = personRepository.getPersons().size();
		
		//Act
		personServiceImpl.addPerson(person1);
		Integer personDataAfter = personRepository.getPersons().size();
		
		//Assert
		assertTrue(personDataAfter == (personData + 1));
	}
	
	@Test //PERSON DELETE
	void testDeletePerson() {
		//Arrange
		List<Person> personList = personRepository.getPersons();
		Integer personData = personList.size();
		
		//Act
		personServiceImpl.deletePerson("Tenley", "Boyd");
		
		//Assert
		assertTrue(personData > personList.size());
	}
	
	@Test //PERSON PUT
	void testUpdatePerson() {
		//Arrange
		List<Person> personList = personRepository.getPersons();
		
		//Act
		Boolean updatePerson = personServiceImpl.updatePerson(person2);
		
		//Assert
		assertTrue(personList.get(0).address.equals("1510 Culver St"));
	}

}
