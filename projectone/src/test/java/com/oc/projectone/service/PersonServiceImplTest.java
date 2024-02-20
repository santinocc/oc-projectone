package com.oc.projectone.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.oc.projectone.model.Person;
import com.oc.projectone.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
class PersonServiceImplTest {
	
	@Mock
	private PersonRepository personRepositoryMock;
	
	@Autowired
	MedicalServiceImpl medicalServiceImpl;    //This and maybe the Persons and Medical Repositories would be required for the data to be validated
	
	@InjectMocks
	PersonServiceImpl personServiceImpl;
	
	
	Person person1 = new Person("Luffy", "MonkeyD", "834 Binoc Ave", "Culver", "111-222-3333", "97451", "luffy@email.com");
	Person person2 = new Person("John", "Boyd", "1510 Culver St", "Culver", "999-888-7777", "97450", "jaboyd2@email.com");
	
	//HTTP METHODS TEST
	
	@Test //PERSON POST
	void testAddPerson() {
		//Arrange
		List<Person> personList = personRepositoryMock.getPersons();
		Integer personData = personRepositoryMock.getPersons().size();
		
		//Act
		personServiceImpl.addPerson(person1);
		Integer personDataAfter = personRepositoryMock.getPersons().size();
		
		//Assert
		assertTrue(personDataAfter == (personData + 1));
		assertTrue(personList.get(-1).firstName.equals("Luffy"));
	}
	
	@Test //PERSON DELETE
	void testDeletePerson() {
		//Arrange
		List<Person> personList = personRepositoryMock.getPersons();
		Integer personData = personList.size();
		
		//Act
		personServiceImpl.addPerson(person1);
		personServiceImpl.deletePerson("Luffy", "MonkeyD");
		
		//Assert
		assertTrue(personData == personList.size());
	}
	
	@Test //PERSON PUT
	void testUpdatePerson() {
		//Arrange
		List<Person> personList = personRepositoryMock.getPersons();
		
		//Act
		Boolean updatePerson = personServiceImpl.updatePerson(person2);
		
		//Assert
		assertTrue(personList.get(0).address.equals("1510 Culver St"));
	}

}
